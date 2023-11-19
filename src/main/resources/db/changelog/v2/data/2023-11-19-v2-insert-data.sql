--liquibase formatted sql

--changeset Artem Lysenko:insert-data-into-subject
insert into subject (name) values ('МАТЕМАТИКА');
insert into subject (name) values ('ИСТОРИЯ');
insert into subject (name) values ('ВЕБ-ПРОГРАММИРОВАНИЕ');
insert into subject (name) values ('ПРОГРАММИРОВАНИЕ');
insert into subject (name) values ('НИЗКОУРОВНЕВОЕ ПРОГРАММИРОВАНИЕ');
insert into subject (name) values ('АРХИТЕКТУРА КОМПЬЮТЕРА');

--changeset Artem Lysenko:insert-data-into-course
insert into course (description) values ('1 курс бакалавриата');
insert into course (description) values ('2 курс бакалавриата');
insert into course (description) values ('3 курс бакалавриата');
insert into course (description) values ('4 курс бакалавриата');
insert into course (description) values ('1 курс магистратуры');
insert into course (description) values ('2 курс магистратуры');

--changeset Artem Lysenko:insert-data-into-topic-type
insert into topic_type (type) values (0);
insert into topic_type (type) values (1);
insert into topic_type (type) values (2);

--changeset Artem Lysenko:insert-data-into-subject-topic
insert into subject_topic (type_id, subject_id, course_number) values (1, 1, 1);
insert into subject_topic (type_id, subject_id, course_number) values (2, 2, 1);
insert into subject_topic (type_id, subject_id, course_number) values (3, 1, 3);
insert into subject_topic (type_id, subject_id, course_number) values (1, 4, 1);
insert into subject_topic (type_id, subject_id, course_number) values (1, 3, 2);
insert into subject_topic (type_id, subject_id, course_number) values (2, 5, 2);

