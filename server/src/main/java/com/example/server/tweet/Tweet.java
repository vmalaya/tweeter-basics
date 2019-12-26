package com.example.server.tweet;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table(value = "tweets")
@RequiredArgsConstructor
public class Tweet implements Persistable<Long> {

    @Id
    private Long id;

    @NonNull
    private String author;

    @NonNull
    private String body;

    @Override
    public boolean isNew() {
        boolean result = Objects.isNull(id);
        this.id = result ? 100l : this.id;
        return result;
    }
}
