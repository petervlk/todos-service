CREATE TABLE todo_lists
(
    id    serial PRIMARY KEY,
    label VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE todo_items
(
    id        serial PRIMARY KEY,
    list_id   INT                 NOT NULL,
    label     VARCHAR(255) UNIQUE NOT NULL,
    completed BOOLEAN             NOT NULL DEFAULT FALSE,
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

INSERT INTO todo_items (list_id, label)
VALUES ((SELECT id FROM todo_lists WHERE label = 'chores' LIMIT 1), 'do the dishes');
