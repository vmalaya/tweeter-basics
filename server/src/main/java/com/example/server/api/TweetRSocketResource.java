package com.example.server.api;

import com.example.server.repository.TweetRepository;
import com.example.server.tweet.Tweet;
import com.example.server.tweet.TweetRequest;
import com.example.server.tweet.TweetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.annotation.Transient;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Controller
@RequiredArgsConstructor
public class TweetRSocketResource {

    private final TweetRepository repository;

    @MessageMapping("tweets")
    public Flux<TweetResponse> getTweet(@RequestBody TweetRequest request) {
        String author = request.getAuthor();
        int size = request.getSize();
        log.info("author {}", request);
        repository.findById(100l).doOnNext(log::warn);
        return repository.findAllAuthorTweets(author, size)
                .map(tweet -> TweetResponse.of(tweet.getAuthor(), tweet.getBody()));
    }

//    @Transactional
    @MessageMapping("send")
    public Mono<Tweet> saveTweet(@RequestBody TweetRequest request) {
        String author = request.getAuthor();
        int size = request.getSize();
        log.info("tweet {}", request);
        Mono<Tweet> savedTweet = repository.save(new Tweet(author, "It is a generated tweet from " + author + "."));
        return savedTweet;
    }
}
