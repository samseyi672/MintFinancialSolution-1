package com.mint.financial.kafka.producer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketMessage;
@Component
@EnableBinding(Source.class)
public class CardSchemekafkaProducer {
	
private static final int SENDING_MESSAGE_TIMEOUT  = 10000 ;
private Source source ;
public CardSchemekafkaProducer(Source source) {
	super();
	this.source = source;
}

//send/publish message to  producers
public void sendCardSchemeMessage(WebSocketMessage<?> message) {
 boolean st  =source.output().send(MessageBuilder.withPayload(message.getPayload()).build(),
		 CardSchemekafkaProducer.SENDING_MESSAGE_TIMEOUT) ;
    if(st){
		System.out.println("message sent  successfully");   
	   }else {
		   System.out.println("message not sent successfully");
	   }
   }

//send/publish message to  producers
public void sendCardSchemeMessage2(String message) {
   boolean st  = source.output().send(MessageBuilder.withPayload(message).build(),
		 CardSchemekafkaProducer.SENDING_MESSAGE_TIMEOUT) ;
   if(st) {
	System.out.println("message sent  successfully");   
   }else {
	   System.out.println("message not sent successfully");
   }
 }
}

















































































































































































