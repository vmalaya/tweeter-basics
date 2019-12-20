package com.example.server.repository;

import com.example.server.tweet.Tweet;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface TweetRepository extends R2dbcRepository<Tweet, Long> {
    @Query("select * from tweets where author = $1")
    Flux<Tweet> findByAuthor(String author);
}
