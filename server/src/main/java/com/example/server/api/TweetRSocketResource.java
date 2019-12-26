package com.example.server.api;

import com.example.server.repository.TweetRepository;
import com.example.server.tweet.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Controller
@RequiredArgsConstructor
public class TweetRSocketResource {

    private final TweetRepository repository;

    @MessageMapping("tweets")
    public Flux<RetrieveTweetsResponse> getTweet(@RequestBody RetrieveTweetsRequest request) {
        String author = request.getAuthor();
        int size = request.getSize();
        log.info("author {}", request);
        return repository.findAllAuthorTweets(author, size).map(
                tweet -> RetrieveTweetsResponse.of(tweet.getAuthor(), tweet.getBody()));
    }

    @MessageMapping("send")
    public Mono<SendTweetResponse> saveTweet(@RequestBody SendTweetRequest request) {
        String author = request.getAuthor();
        String body = request.getBody();
        log.info("tweet {}", request);
        Mono<Tweet> savedTweet = repository.save(new Tweet(author, body));
        return savedTweet.map(tweet -> SendTweetResponse.of(tweet.getAuthor(), tweet.getBody()));
    }
}
