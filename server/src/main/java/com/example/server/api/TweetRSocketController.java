package com.example.server.api;

import com.example.server.repository.TweetRepository;
import com.example.server.tweet.Tweet;
import com.example.server.tweet.TweetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.Collections;

@Controller
public class TweetRSocketController {
    @Autowired
    private TweetRepository repository;

    @MessageMapping("tweets")
    public Flux<Tweet> getTweet(TweetRequest request) {
        return repository.findMikesTweets();
//        return repository.findAllById(Collections.singleton(Long.valueOf(request.getAuthor())));
//        return repository.findByAuthor(request.getAuthor());
//        return Flux.just(new Tweet(request.getAuthor(), "Tweet From "+ request.getAuthor()));
    }
}
