package com.example.client;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.util.MimeTypeUtils;

import java.net.URISyntaxException;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    RSocket rSocket() {
        return RSocketFactory
                .connect()
                .mimeType(MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_JSON_VALUE)
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .transport(TcpClientTransport.create(7000))
                .start()
                .block();
    }

    @Bean
    RSocketRequester requester(RSocketStrategies strategies) throws URISyntaxException {
        return RSocketRequester.builder().rsocketStrategies(strategies)
                .rsocketFactory(factory -> {
                    factory.dataMimeType(MimeTypeUtils.ALL_VALUE)
                            .frameDecoder(PayloadDecoder.ZERO_COPY);})
                .connect(TcpClientTransport.create(7000))
                .retry()
                .block();
    }
}
