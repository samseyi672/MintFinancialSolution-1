package com.mint.financial;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RequestHandlerConfigForReactiveApiRouter {
	  @Bean
	  public RouterFunction<ServerResponse> route(RequestHandler requestHandler) {
	    return RouterFunctions
	      .route(RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), requestHandler::hello);
	  }
}
