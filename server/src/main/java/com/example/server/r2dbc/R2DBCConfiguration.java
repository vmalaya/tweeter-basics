package com.example.server.r2dbc;

// import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
// import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Slf4j
// @Configuration
// @RequiredArgsConstructor
// public class R2DBCConfiguration {
//     final DatabaseProperties databaseProperties;
//
//     @Bean
//     public ConnectionFactory connectionFactory(){
//         return new PostgresqlConnectionFactory(
//                 PostgresqlConnectionConfiguration
//                         .builder()
//                         .database(databaseProperties.name)
//                         .username(databaseProperties.user)
//                         .password(databaseProperties.password)
//                         .host(databaseProperties.server.host)
//                         .port(databaseProperties.server.port)
//                         .build());
//     }
// }
