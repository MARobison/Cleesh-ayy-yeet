/*
Insertions for TASK table
*/
insert into task ( task_label, create_date, due_date) values ('Assignment 1', CURDATE(), '2018-09-04');
insert into task (task_label, create_date, due_date) values ('Assignment 3', CURDATE(),  '2018-08-02');
insert into task (task_label, create_date, due_date) values ('Project 1',CURDATE(),  '2018-09-22');
insert into task (task_label, create_date, due_date) values ('Midterm 2',CURDATE(),  '2018-09-25');
insert into task (task_label, create_date, due_date) values ('Final',CURDATE(),  '2018-10-19');
insert into task (task_label, create_date, due_date) values ('Assignment 2',CURDATE(),  '2018-05-17');
insert into task (task_label, create_date, due_date) values ('Midterm 1',CURDATE(),  '2018-05-15');
insert into task (task_label, create_date, due_date) values ('Final Project',CURDATE(),  '2018-08-24');
insert into task (task_label, create_date, due_date) values ('Project 2',CURDATE(),  '2018-07-06');
insert into task (task_label, create_date, due_date) values ('Extra Credit',CURDATE(),  '2018-09-07');
insert into task (task_label, create_date, due_date) values ('Spring Cleaning','2018-01-01', '2018-05-05');

/*
Insertions for TAG table
*/
insert into tag (tag_id, tag_label) values (1, 'school');
insert into tag (tag_id, tag_label) values (2, 'homework');
insert into tag (tag_id, tag_label) values (3, 'exam');
insert into tag (tag_id, tag_label) values (4, 'project');
insert into tag (tag_id, tag_label) values (5, 'final');
insert into tag (tag_id, tag_label) values (6, 'PRIORITY');
insert into tag (tag_id, tag_label) values (7, 'extra credit');
insert into tag (tag_id, tag_label) values (8, 'chore');

/*
Insertions for TAGGED table
*/
insert into assign(task_id, tag_id) values (1, 1);
insert into assign(task_id, tag_id) values (1, 2);
insert into assign(task_id, tag_id) values (2, 1);
insert into assign(task_id, tag_id) values (2, 2);
insert into assign(task_id, tag_id) values (3, 1);
insert into assign(task_id, tag_id) values (3, 4);
insert into assign(task_id, tag_id) values (4, 1);
insert into assign(task_id, tag_id) values (4, 3);
insert into assign(task_id, tag_id) values (5, 1);
insert into assign(task_id, tag_id) values (5, 5);
insert into assign(task_id, tag_id) values (5, 6);
insert into assign(task_id, tag_id) values (6, 1);
insert into assign(task_id, tag_id) values (6, 2);
insert into assign(task_id, tag_id) values (7, 1);
insert into assign(task_id, tag_id) values (7, 3);
insert into assign(task_id, tag_id) values (8, 1);
insert into assign(task_id, tag_id) values (8, 4);
insert into assign(task_id, tag_id) values (8, 6);
insert into assign(task_id, tag_id) values (9, 1);
insert into assign(task_id, tag_id) values (9, 4);
insert into assign(task_id, tag_id) values (10, 1);
insert into assign(task_id, tag_id) values (10, 7);
insert into assign(task_id, tag_id) values (11, 8);
