DROP TABLE IF EXISTS COMMENT;

DROP TABLE IF EXISTS BLOG;

CREATE TABLE BLOG
(
    id          BIGSERIAL PRIMARY KEY,
    blog_id     UUID         NOT NULL,
    subject     VARCHAR(100) NOT NULL,
    description VARCHAR     DEFAULT NULL,
    create_date VARCHAR(25) DEFAULT NULL,
    update_date VARCHAR(25) DEFAULT NULL,
    delete_date VARCHAR(25) DEFAULT NULL,
    author      VARCHAR(50) DEFAULT NULL,
    status      VARCHAR(15) DEFAULT NULL
);

CREATE TABLE COMMENT
(
    id          BIGSERIAL PRIMARY KEY,
    comment_id  UUID NOT NULL,
    text        VARCHAR     DEFAULT NULL,
    create_date VARCHAR(25) DEFAULT NULL,
    update_date VARCHAR(25) DEFAULT NULL,
    delete_date VARCHAR(25) DEFAULT NULL,
    author      VARCHAR(50) DEFAULT NULL,
    blog_id     BIGINT NOT NULL
);