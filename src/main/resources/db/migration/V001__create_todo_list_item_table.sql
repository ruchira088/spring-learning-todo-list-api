
CREATE table todo_list_item(
    id VARCHAR(40) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    status VARCHAR(24) NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT NULL,
    completed_at TIMESTAMP NULL,
    PRIMARY KEY (id)
);