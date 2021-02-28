package com.example.server.repository;

import com.example.server.tweet.Tweet;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface TweetRepository extends R2dbcRepository<Tweet, UUID> {

    @Query("SELECT * FROM tweets WHERE LOWER(author) LIKE CONCAT('%',LOWER(:author),'%') LIMIT :size")
    Flux<Tweet> findAllAuthorTweets(String author, Integer size);
}
