CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE todo_lists
(
    id        UUID PRIMARY KEY             DEFAULT uuid_generate_v4(),
    label     VARCHAR(255) UNIQUE NOT NULL,
    timestamp TIMESTAMP           NOT NULL DEFAULT now()
);

CREATE TABLE todo_items
(
    id        UUID PRIMARY KEY             DEFAULT uuid_generate_v4(),
    list_id   UUID                NOT NULL,
    label     VARCHAR(255) UNIQUE NOT NULL,
    completed BOOLEAN             NOT NULL DEFAULT FALSE,
    timestamp TIMESTAMP           NOT NULL DEFAULT now(),
    FOREIGN KEY (list_id)
        REFERENCES todo_lists (id)
);

INSERT INTO todo_lists (label)
VALUES ('chores');

INSERT INTO todo_lists (label)
VALUES ('webapp');

INSERT INTO todo_items (list_id, label)
VALUES ((SELECT id FROM todo_lists WHERE label = 'webapp' LIMIT 1), 'configure DB');

INSERT INTO todo_items (list_id, label)
VALUES ((SELECT id FROM todo_lists WHERE label = 'chores' LIMIT 1), 'take out trash');

INSERT INTO todo_items (list_id, label, completed)
VALUES ((SELECT id FROM todo_lists WHERE label = 'chores' LIMIT 1), 'do the dishes', true );
