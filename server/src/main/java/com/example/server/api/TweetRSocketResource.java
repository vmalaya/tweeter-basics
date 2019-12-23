package com.example.server.api;

import com.example.server.repository.TweetRepository;
import com.example.server.tweet.Tweet;
import com.example.server.tweet.TweetRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sun.tools.jinfo.JInfo;

@Log4j2
@Controller
@RequiredArgsConstructor
public class TweetRSocketResource {

    private final TweetRepository repository;

    @MessageMapping("tweets")
    public Flux<Tweet> getTweet(TweetRequest request) {
        String author = request.getAuthor();
        int size = request.getSize();
        log.info("author {}", request);
        return repository.findAllAuthorTweets(author, size)
                         .doOnNext(log::info);
        //        return repository.findAllById(Collections.singleton(Long.valueOf(request.getAuthor())));
        //        return repository.findByAuthor(request.getAuthor());
        //        return Flux.just(new Tweet(request.getAuthor(), "Tweet From "+ request.getAuthor()));
    }
}
