package com.example.client.api;

import com.example.client.tweet.SendTweetRequest;
import com.example.client.tweet.SendTweetResponse;
import com.example.client.tweet.RetrieveTweetsRequest;
import com.example.client.tweet.RetrieveTweetsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
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
        Flux<RetrieveTweetsResponse> tweets = requester.flatMapMany(rSocket -> rSocket.route("tweets")
                .data(Mono.just(RetrieveTweetsRequest.of(author)), RetrieveTweetsRequest.class)
                .retrieveFlux(RetrieveTweetsResponse.class));
                // .onBackpressureBuffer(1, log::warn, BufferOverflowStrategy.DROP_OLDEST)
                // .onErrorContinue((throwable, obj) -> {
                //     log.error("throwable: {}", throwable.getLocalizedMessage());
                //     log.error("obj: {}", obj);
                // })
                // take(2) // pagination
                ;
        return ServerResponse.ok()
                //.contentType(MediaType.APPLICATION_STREAM_JSON)
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(tweets, RetrieveTweetsResponse.class);
    }

    public Mono<ServerResponse> saveTweet(ServerRequest request) {
        String author = request.pathVariable("author");
        String body = request.pathVariable("body");
        Mono<SendTweetResponse> sentTweet = requester.flatMap(rSocket -> rSocket.route("send")
                .data(Mono.just(SendTweetRequest.of(author, body)))
                .retrieveMono(SendTweetResponse.class));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(sentTweet, SendTweetResponse.class);

    }

    public Mono<ServerResponse> getFallback(ServerRequest serverRequest) {
        URI uri = serverRequest.uri();
        String baseUrl = String.format("%s://%s", uri.getScheme(), uri.getAuthority());
        Map<String, String> api = new LinkedHashMap<>();
        api.put("/tweets/{author}", String.format("%s/tweets/{author}", baseUrl));
        return ServerResponse.ok().body(Mono.just(api), LinkedHashMap.class);
    }
}
