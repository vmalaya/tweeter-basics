package com.example.server.api;

import com.example.server.repository.TweetRepository;
import com.example.server.tweet.Tweet;
import com.example.server.tweet.TweetRequest;
import com.example.server.tweet.TweetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Log4j2
@Controller
@RequiredArgsConstructor
public class TweetRSocketResource {

    private final TweetRepository repository;

    @MessageMapping("tweets")
    public Flux<TweetResponse> getTweet(TweetRequest request) {
        String author = request.getAuthor();
        int size = request.getSize();
        log.info("author {}", request);
        return repository.findAllAuthorTweets(author, size).map(tweet -> TweetResponse.of(tweet.getAuthor(), tweet.getBody()));
    }
}
