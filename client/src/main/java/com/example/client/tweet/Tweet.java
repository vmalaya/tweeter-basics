package com.example.client.tweet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
    private Long id;
    private String author;
    private String body;
}
