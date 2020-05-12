insert into users (name, password)
values ('admin', 'admin'),
       ('user', 'password'),
       ('user2', 'password');

insert into user_role (user_id, roles)
values (100000, 'ADMIN'),
       (100000, 'USER'),
       (100001, 'USER'),
       (100002, 'USER');