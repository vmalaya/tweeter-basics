package com.example.server.tweet;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table(value = "tweets")
@RequiredArgsConstructor
public class  Tweet {

    @Id
    private Long id;

    @NonNull
    private String author;

    @NonNull
    private String body;
}
