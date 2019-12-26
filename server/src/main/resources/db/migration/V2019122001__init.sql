DROP TABLE IF EXISTS tweets;

CREATE TABLE tweets (
    id UUID PRIMARY KEY,
    author varchar(255) NOT NULL,
    body varchar(255) NOT NULL
)
