DROP TABLE IF EXISTS tweets;

CREATE TABLE tweets (
    id serial PRIMARY KEY,
    author varchar(255) NOT NULL,
    body varchar(255) NOT NULL
)
