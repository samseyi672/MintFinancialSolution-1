package com.mint.financial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.mint.financial.webscoket.CardSchemerWebAPICollectorHandler;

@Configuration
@EnableWebSocket
public class WebSocketServerConfiguration  implements WebSocketConfigurer{

	       @Autowired
	    protected CardSchemerWebAPICollectorHandler webSocketHandler;
	   
	        @Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {     
	        registry.addHandler(webSocketHandler, "/");		
	}

}
