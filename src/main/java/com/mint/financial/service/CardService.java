package com.mint.financial.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mint.financial.SSLContextHelper;
import com.mint.financial.entity.CardScheme;
import com.mint.financial.entity.FinalPayload;
import com.mint.financial.entity.FormattedCardScheme;
import com.mint.financial.entity.NoOfHits;
import com.mint.financial.entity.PayLoad;
import com.mint.financial.repository.PayloadRepository;
import com.mint.financial.entity.CardDetails;

@Service
public class CardService {

	@Autowired
	private RestTemplate template;
	@Autowired
	private SSLContextHelper ssl;
	@Autowired
	private FormattedCardScheme formattedCard;

	@Autowired
	private CardScheme card;

	@Autowired
	private FinalPayload payload;
	
	@Autowired
	private CardDetails details;
	
	@Autowired
	private PayloadRepository payloadRepository;
	
	@Autowired
   private NoOfHits hitsCalculation  ;
	
	@Autowired
	private PayLoad payLoad ;
  private final static String COUNT = "1"  ;
	public CardScheme findCardDetails(String url, String cardNumber) {
		// create the card instance here
		ssl.disable();
		String result = template.getForObject(url + "/" + cardNumber, String.class);
		System.out.println("from " + result);
		CardScheme card = template.getForObject(url + "/" + cardNumber, CardScheme.class);
		return card;
	}

	public CardDetails findCardDetailsFormatted(String url, String cardNumber) {
		// create the card instance here
		ssl.disable();
		String result = template.getForObject(url + "/" + cardNumber, String.class);
		System.out.println("from " + result);
		card = template.getForObject(url + "/" + cardNumber, CardScheme.class);
		details.setSuccess(true);
		formattedCard.setType(card.getType());
		formattedCard.setScheme(card.getScheme());
		formattedCard.setBank(card.getBank().get("name"));
		details.setPayLoad(formattedCard);
		return details;
	}
     public List<NoOfHits> getallPagination(int start , int limits){
    	Pageable pageable  = PageRequest.of(start,limits)  ;
    	return payloadRepository.findAll(pageable).getContent() ;
		
     }
     public void  calculateNoOfHits(String cardNumber,CardDetails details) {
    	 // logic  to  log the  number  of request 
         List<NoOfHits> hits  =  payloadRepository.findByPayLoadCardNumber(cardNumber) ;
         System.out.println("from mongo " + details.getPayLoad().getBank());
         System.out.println(hits);
  		 if(!hits.isEmpty()){
  			hits.stream().forEach(e->{
  			  payLoad  =   e.getPayload()  ;
  			 payLoad.setCardCount(String.valueOf(Integer.parseInt(payLoad.getCardCount())+1));
  			 e.setPayload(payLoad);
  			 payloadRepository.save(e) ;
  			// payLoad = null ; // for reusability
  		System.out.println("updated into mongo db");	
  		System.out.println("inside stream "+ payLoad.getCardNumber());
  			     });
  		       }else {
  		    PayLoad load  = new PayLoad() ;
  		  load.setBank(details.getPayLoad().getBank());
  		load.setCardNumber(cardNumber);
  		load.setType(details.getPayLoad().getType());
  		load.setCardCount(COUNT);
  		      System.out.println("inside stream "+ load.getCardNumber());
  		    System.out.println("inside stream "+ load.getType());
  		  System.out.println("inside stream "+ load.getBank());
  		    	hitsCalculation.setPayload(load);
  		    	hitsCalculation.setSuccess("");
  		    	hitsCalculation.setLimits("");
  		    	hitsCalculation.setStart("");
  		    	payloadRepository.save(hitsCalculation) ;
  		    	System.out.println("insert into mongo db");	
  		       }
          }
	public List<FinalPayload> formatJson(List<NoOfHits> lists) {
		  System.out.println(lists);
//		List<FinalPayload>  load = lists.parallelStream().map(e->{
//			   payload.setCard(e.getPayload().getCardNumber());
//			   payload.setCount(e.getPayload().getCardCount());
//			   System.out.println(payload.getCard());
//			     return payload ;
//				     // return p  ;
//				     }).collect(Collectors.toList());
		return  null ;
	}
}




