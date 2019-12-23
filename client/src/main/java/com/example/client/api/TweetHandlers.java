package com.example.client.api;

import com.example.client.tweet.TweetRequest;
import com.example.client.tweet.TweetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.expression.spel.ast.TypeReference;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class TweetHandlers {

    private final Mono<RSocketRequester> requester;

    public Mono<ServerResponse> getTweet(ServerRequest serverRequest) {
        String author = serverRequest.pathVariable("author");
        Flux<TweetResponse> tweets = requester.flatMapMany(rSocket -> rSocket.route("tweets")
                                                                             .data(Mono.just(TweetRequest.of(author)), TweetRequest.class)
                                                                             .retrieveFlux(TweetResponse.class))
                                              // .onBackpressureBuffer(1, log::warn, BufferOverflowStrategy.DROP_OLDEST)
                                              // .onErrorContinue((throwable, obj) -> {
                                              //     log.error("throwable: {}", throwable.getLocalizedMessage());
                                              //     log.error("obj: {}", obj);
                                              // })
                                              .take(2) // pagination
                ;
        return ServerResponse.ok()
                             //.contentType(MediaType.APPLICATION_STREAM_JSON)
                             .contentType(MediaType.TEXT_EVENT_STREAM)
                             .body(tweets, TweetResponse.class);
        // return ServerResponse.ok().body(tweets.("tweets")
        //                 .data(new TweetRequest(author)).retrieveFlux(Tweet.class), Tweet.class);
    }

    public Mono<ServerResponse> getFallback(ServerRequest serverRequest) {
        URI uri = serverRequest.uri();
        String baseUrl = String.format("%s://%s", uri.getScheme(), uri.getAuthority());
        Map<String, String> api = new LinkedHashMap<>();
        api.put("/tweets/{author}", String.format("%s/tweets/{author}", baseUrl));
        return ServerResponse.ok().body(Mono.just(api), LinkedHashMap.class);
    }
}
