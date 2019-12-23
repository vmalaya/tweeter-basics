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
