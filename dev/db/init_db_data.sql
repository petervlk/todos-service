CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE todo_simple_items
(
    label   VARCHAR(50) PRIMARY KEY NOT NULL,
    content VARCHAR(255) NOT NULL
);

INSERT INTO todo_simple_items (label, content)
VALUES ('system', 'use integrant');

INSERT INTO todo_simple_items (label, content)
VALUES ('db', 'use connection pool');
