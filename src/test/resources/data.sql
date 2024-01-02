CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

call next value for hibernate_sequence; -- hibernate_sequence 라는 시퀀스를 호출한다.
insert into member (`name`, `email`, `created_at`, `updated_at`) values ( 'martin', 'martin@fastcampus.com', now(), now());

call next value for hibernate_sequence;
insert into member ( `name`, `email`, `created_at`, `updated_at`) values ( 'dennis', 'dennis@fastcampus.com', now(), now());

call next value for hibernate_sequence;
insert into member ( `name`, `email`, `created_at`, `updated_at`) values ( 'sophia', 'sophia@slowcampus.com', now(), now());

call next value for hibernate_sequence;
insert into member ( `name`, `email`, `created_at`, `updated_at`) values ( 'james', 'james@slowcampus.com', now(), now());

call next value for hibernate_sequence;
insert into member (`name`, `email`, `created_at`, `updated_at`) values ( 'martin', 'martin@another.com', now(), now());
