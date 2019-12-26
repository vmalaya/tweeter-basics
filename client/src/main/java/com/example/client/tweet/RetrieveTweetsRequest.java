package com.example.client.tweet;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName = "of")
public class RetrieveTweetsRequest {
    @NonNull
    private String author;
    private Integer size = 25;
}
