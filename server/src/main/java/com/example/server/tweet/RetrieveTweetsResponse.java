package com.example.server.tweet;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName = "of")
public class RetrieveTweetsResponse {
    private String author;

    @NonNull
    private String body;
}
