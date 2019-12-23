
```cmd
mvnw -f postgres/pom.xml docker-compose:up
mvnw -f server/pom.xml process-resources flyway:migrate
```

