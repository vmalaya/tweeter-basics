package com.example.client.tweet;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class TweetResponse {

    private Long id;
    private String author;

    @NonNull
    private String body;
}
