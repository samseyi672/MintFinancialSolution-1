package com.mint.financial.webscoket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

//import com.mint.financial.webscoket.CardSchemerWebAPICollectorHandler;


public class ClientStreamCaller {
	
	@Autowired
 private CardSchemerWebAPICollectorHandler cardApiSocketHandler  ;    
	     
	//@Autowired
 //private WebSocketClient cardAPISocket  ;
	
 public  void call(String MEET_ENDPOINTS) { 
	 System.out.println(cardApiSocketHandler);
	 System.out.println(MEET_ENDPOINTS);
	 WebSocketClient cardAPISocket  = new StandardWebSocketClient() ;
	 System.out.println(cardApiSocketHandler);
	 cardAPISocket.doHandshake(cardApiSocketHandler, MEET_ENDPOINTS) ;
    }
}


















































