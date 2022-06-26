INSERT INTO users (name,surname,email,password,zip_code,phone_number)
VALUES ('Admin', 'Administrator','blog@blog.lt','{bcrypt}$2a$10$SGc9uu/KSfGl/452i94kLOINr.eUyREI/HZKJhoEgLwI8BaNlpZ8C','LT-12345','861012345'), --pass -> blog
('User', 'User','user@blog.lt','{bcrypt}$2y$10$W2cZU7MWzkAvtw/znuvKgu1lwLlS1x7DZvXrMTwCyvZ9xYRzi9yAK','LT-12345','861012345'); --pass -> user

INSERT INTO authority (name)
VALUES ('ADMIN'),
       ('USER');

INSERT INTO users_authorities
VALUES (1, 1),
       (1, 2),
       (2, 2);