package com.mint.financial;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import com.mint.financial.entity.CardDetails;
import com.mint.financial.entity.CardScheme;
import com.mint.financial.entity.FinalOutput;
import com.mint.financial.entity.FinalPayload;
import com.mint.financial.entity.FormattedCardScheme;
import com.mint.financial.entity.NoOfHits;
import com.mint.financial.entity.PayLoad;
import com.mint.financial.webscoket.CardSchemerWebAPICollectorHandler;
import com.mint.financial.webscoket.ClientStreamCaller;

@SpringBootApplication
@EnableMongoRepositories("com.mint.financial.repository")
public class MintFinancialSolution1Application {
	private static String CARD_ENDPOINTS  = "ws://localhost:8091/cardscheme/verify/5399832609313790" ;
	private static String CARD_ENDPOINTS2  = "ws://localhost:8091/cardscheme/publish/5399832609313790" ;
	private static String  MEET_ENDPOINTS  = "ws://stream.meetup.com/2/rsvps" ;
	private static String MEET_ENDPOINTS2  = "ws://localhost:8091/cardscheme/verify" ;
	private static String MEET_ENDPOINTS3  = "ws://localhost:8091/cardscheme/emit" ;
	private static String MEET_ENDPOINTS4  = "ws://localhost:8091/cardscheme/hello" ;
	public static void main(String[] args) {
		SpringApplication.run(MintFinancialSolution1Application.class, args);
	}
	   @Bean
	   public RestTemplate getRestTemplate() {
	    	
	      return new RestTemplate();
	   }
	    @Bean
	   public SSLContextHelper getSSLContextHelper() {
	    	 return new SSLContextHelper() ;
	    }
	   @Bean
	  public CardScheme getCardScheme() {
		  return new CardScheme() ;  
	   }
	       @Bean
		  public CardDetails getCardDetails() {
			  return new CardDetails() ;  
		   }  
	            @Bean
			  public FormattedCardScheme getFormattedCardScheme() {
				  return new FormattedCardScheme() ;  
			   } 	                         
	     @Bean
	   public WebSocketClient getWebSocketClient() {
	    	 return  new StandardWebSocketClient() ;
	     }
//	     @Bean
//	   public FinalPayload  getFinalPayload() {
//	    	  return new FinalPayload() ;
//	     }
	     @Bean
	  public ClientStreamCaller getClientStreamCaller() {
		  return new ClientStreamCaller() ;
	        }
	     
	    @Bean
	 public NoOfHits  getNoOfHits() {
	    	return new NoOfHits() ;
	        }
       @Bean
     public PayLoad getPayload() {
    	   return  new  PayLoad() ;
       }
	 	    @Bean
	 	public ApplicationRunner initializeConnection(CardSchemerWebAPICollectorHandler handler){
	 		return args -> {
	 			// while(true) {
	 			 WebSocketClient cardAPISocket  = new StandardWebSocketClient() ;
	 			 System.out.println("handler api working also with application runner");
	 			 cardAPISocket.doHandshake(handler,CARD_ENDPOINTS2) ;
	 			 
	 			// }
	 		};
	}
}


















































