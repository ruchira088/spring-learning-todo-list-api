CREATE TABLE todo_list(
    id VARCHAR(40) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT NULL,

    PRIMARY KEY (id)
);