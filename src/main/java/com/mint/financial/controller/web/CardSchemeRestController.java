package com.mint.financial.controller.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mint.financial.MintFinancialSolution1Application;
import com.mint.financial.SSLContextHelper;
import com.mint.financial.MintFinancialSolution1Application;
import com.mint.financial.entity.CardDetails;
import com.mint.financial.entity.CardScheme;
import com.mint.financial.entity.FinalOutput;
import com.mint.financial.entity.FinalPayload;
import com.mint.financial.entity.NoOfHits;
import com.mint.financial.kafka.producer.CardSchemekafkaProducer;
import com.mint.financial.repository.PayloadRepository;
import com.mint.financial.service.CardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cardscheme")
@CrossOrigin
public class CardSchemeRestController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplaplate;
	
	@Autowired
   private FinalOutput out ;
	
	@Autowired
	FinalPayload payload   ;
	
	@Autowired
	CardSchemekafkaProducer producer;
	private static final Logger log = LoggerFactory.getLogger(MintFinancialSolution1Application.class);
	private ExecutorService executor = Executors.newCachedThreadPool();
	
	@Autowired
	CardService cardService;

	public KafkaTemplate<String, String> getKafkaTemplate() {
		return kafkaTemplaplate;
	}

	@GetMapping("/publish/{cardNumber}")
	public String pubishCardAndCheckStatus(@PathVariable String cardNumber) throws JsonProcessingException {
		CardDetails details = cardService.findCardDetailsFormatted("https://lookup.binlist.net", cardNumber);
		System.out.println("printing  out the kafka result");
		System.out.println(details.getPayLoad().getScheme());
		String json = new ObjectMapper().writeValueAsString(details.getPayLoad());
		System.out.println(details.getPayLoad().getScheme());
		producer.sendCardSchemeMessage2(json); // produce the message
		return "";
	}

	@GetMapping("/format/verify/{cardNumber}") //
	public CardScheme getCardDetailsByNumber(@PathVariable String cardNumber) {
		// calling the service
		CardScheme card = cardService.findCardDetails("https://lookup.binlist.net", cardNumber);
		System.out.println("printing  out the api result");
		log.info(card.toString());
		ResponseEntity<CardScheme> response = new ResponseEntity<CardScheme>(card, HttpStatus.OK);
		return card;
	}

	@GetMapping("/verify/{cardNumber}") //
	public String getCardDetailsFormat(@PathVariable String cardNumber) throws JsonProcessingException {
		// calling the service
		CardDetails details = cardService.findCardDetailsFormatted("https://lookup.binlist.net", cardNumber);
		System.out.println(details.getPayLoad().getScheme());
		ResponseEntity<CardDetails> response = new ResponseEntity<CardDetails>(details, HttpStatus.OK);
		System.out.println("testing the api ");
		cardService.calculateNoOfHits(cardNumber, details);
       String json = new ObjectMapper().writeValueAsString(details);
       
       producer.sendCardSchemeMessage2("sending  from rest controller "+details);
		System.out.println(json);
		return json;
	}

	@GetMapping("")
	public ResponseEntity<?> seenPath() {
		return new ResponseEntity<String>("seen", HttpStatus.OK);
	}

	@GetMapping("/card-scheme/start") // this will return the number of hits
	public List<NoOfHits> noOfHits(@RequestParam String start, @RequestParam String limits) {
		List<NoOfHits> lists = cardService.getallPagination(Integer.parseInt(start)-1, Integer.parseInt(limits));
		//List<FinalPayload> load =  new ArrayList<>() ;
		lists.forEach(e->{
			e.setSize((String.valueOf(lists.size())));
            e.setStart(start);
            e.setLimits(limits);
            e.setSuccess("true");
		});
		//System.out.println(load);
           return lists ;
	}

}
