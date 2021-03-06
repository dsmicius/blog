DROP TABLE IF EXISTS COMMENT;

DROP TABLE IF EXISTS BLOG;

DROP TABLE IF EXISTS users_authorities;

DROP TABLE  IF EXISTS authority;

DROP TABLE IF EXISTS users_authorities;

CREATE TABLE BLOG
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    blog_id     UUID         NOT NULL,
    subject     VARCHAR(100) NOT NULL,
    description VARCHAR     DEFAULT NULL,
    create_date VARCHAR(25) DEFAULT NULL,
    update_date VARCHAR(25) DEFAULT NULL,
    delete_date VARCHAR(25) DEFAULT NULL,
    author      VARCHAR(50) DEFAULT NULL,
    status      VARCHAR(15) DEFAULT NULL,
    user_id     BIGINT NOT NULL
);

CREATE TABLE COMMENT
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    comment_id  UUID NOT NULL,
    text        VARCHAR     DEFAULT NULL,
    create_date VARCHAR(25) DEFAULT NULL,
    update_date VARCHAR(25) DEFAULT NULL,
    delete_date VARCHAR(25) DEFAULT NULL,
    author      VARCHAR(50) DEFAULT NULL,
    blog_id     BIGINT NOT NULL,
    user_id     BIGINT NOT NULL
);

CREATE TABLE users
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    name              VARCHAR(20)    NOT NULL,
    surname           VARCHAR(50)    NOT NULL,
    email             VARCHAR(100)   NOT NULL,
    password          VARCHAR(255)   NOT NULL,
    zip_code          VARCHAR(10)    NOT NULL,
    phone_number      VARCHAR(12)    NOT NULL
);

CREATE TABLE authority
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE users_authorities
(
    user_id     BIGINT NOT NULL ,
    authorities_id BIGINT NOT NULL
);