package com.example.client.api;

import com.example.client.tweet.Tweet;
import com.example.client.tweet.TweetRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class TweetHandler {
    @Autowired
    private RSocketRequester rSocketRequester;

    public Mono<ServerResponse> getTweet(ServerRequest serverRequest) {
        String author = serverRequest.pathVariable("author");
        return ServerResponse.ok().body(rSocketRequester.route("tweets")
                        .data(new TweetRequest(author)).retrieveFlux(Tweet.class), Tweet.class);
    }
}
