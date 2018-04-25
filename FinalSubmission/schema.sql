/*
IF WE NEED TO CHANGE THE DATABASE execute this query:
DROP DATABASE taskManager;
*/
#CREATE DATABASE IF NOT EXISTS taskManager;
use taskManager;

CREATE TABLE task(
	task_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    task_label VARCHAR(100) NOT NULL,
    create_date DATE,
    due_date DATE,
    task_status VARCHAR(50) NOT NULL DEFAULT 'active'
);

CREATE TABLE tag(
	tag_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    tag_label VARCHAR(50) NOT NULL
);

CREATE TABLE tagged(
	task_id INTEGER NOT NULL REFERENCES task,
    tag_id INTEGER NOT NULL REFERENCES tag,
    PRIMARY KEY(task_id, tag_id)
);