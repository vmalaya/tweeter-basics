package com.example.client.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Configuration
public class RSocketRequesterConfig {

    @Bean
    Mono<RSocketRequester> requester(RSocketRequester.Builder builder,
                                     @Value("${server.port:7000}") Integer port,
                                     @Value("${server.host:127.0.0.1}") String host) {

        return builder.dataMimeType(MediaType.APPLICATION_JSON)
                      .connectTcp(host, port)
                      .retryBackoff(2, Duration.ofSeconds(2));
    }
}
