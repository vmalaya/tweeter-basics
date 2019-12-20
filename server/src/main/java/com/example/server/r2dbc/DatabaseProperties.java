package com.example.server.r2dbc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "database")
public class DatabaseProperties {

    String name;
    String user;
    String password;
    String url;
    Server server;

    @Data
    public static class Server {
        String host;
        Integer port;
    }
}
