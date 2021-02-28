# Tweeter-basics

Client-server application. Provides [API] to frontend developer in order to cover functionality of creating and retrieving tweet for specific user.

Project includes following technologies:
- Spring WebFlux
- Spring rSocket
- R2DBC
- Flyway
- PostgreSQL
 
## dev mode

_windows_

```cmd
mvnw -f postgres/pom.xml docker-compose:up
mvnw -f server/pom.xml process-resources flyway:migrate
```

_unix_

```cmd
./mvnw -f postgres docker-compose:up
./mvnw -f server process-resources flyway:migrate
./mvnw -f server spring-boot:run
./mvnw -f client spring-boot:run
```

## prod mode

```cmd
./mvnw -f postgres docker-compose:up
./mvnw -f server process-resources flyway:migrate
./mvnw -f server package
java -jar server/target/*.jar -Dspring.profiles.include=prod
```
<!--
references
-->

[API]: api.http
