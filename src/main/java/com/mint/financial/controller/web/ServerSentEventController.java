package com.mint.financial.controller.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.ApplicationEvent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerSentEventController {
    private final Flux<ApplicationEvent> events  = null ;
    private final ObjectMapper objectMapper = null ;
//
//    public ServerSentEventController(ProfileCreatedEventPublisher eventPublisher, ObjectMapper objectMapper) {
//        this.events = Flux.create(eventPublisher).share(); 
//        this.objectMapper = objectMapper;
//    }
//
//    @GetMapping(path = "/profiles", produces = MediaType.TEXT_EVENT_STREAM_VALUE)  
//    public Flux<String> profiles() {
//        return this.events.map(pce -> {
//            try {
//                return objectMapper.writeValueAsString(pce) + "\n\n"; 
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }
}
