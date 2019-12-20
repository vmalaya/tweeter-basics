package com.example.client.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TweetRouter {
    @Bean
    RouterFunction<ServerResponse> routes(TweetHandler handler) {
        return route().GET("/tweets/{author}", handler::getTweet).build();
    }
}
