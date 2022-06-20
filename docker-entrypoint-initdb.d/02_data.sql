INSERT INTO roles(name)
VALUES ('CLIENT'),
       ('SUPPORT'),
       ('ADMIN');

INSERT INTO users(login, name, password)
VALUES ('JamesGosling','James Gosling','Java8'),
       ('RodJohnson', 'Rod Johnson', 'Spring1');



INSERT INTO users_roles(user_login, role_id)
VALUES ('JamesGosling', 3),
       ('JamesGosling', 2),
       ('RodJohnson', 3);


