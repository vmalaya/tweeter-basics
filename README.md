
_windows_

```cmd
mvnw -f postgres/pom.xml docker-compose:up
mvnw -f server/pom.xml process-resources flyway:migrate
```

_unix_

```cmd
./mvnw -f postgres docker-compose:up
./mvnw -f server process-resources flyway:migrate
```
