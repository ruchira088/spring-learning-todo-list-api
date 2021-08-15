CREATE TABLE todo_list_item_mappings(
    todo_list_id VARCHAR(40) NOT NULL,
    todo_list_item_id VARCHAR(40) NOT NULL,

    PRIMARY KEY (todo_list_id, todo_list_item_id),

    CONSTRAINT fk_todo_list_id FOREIGN KEY (todo_list_id) REFERENCES todo_list(id),
    CONSTRAINT fk_todo_list_item_id FOREIGN KEY (todo_list_item_id) REFERENCES todo_list_item(id)
);