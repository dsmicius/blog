CREATE TABLE IF NOT EXISTS BLOG
(
    id          BIGSERIAL PRIMARY KEY,
    blog_id     UUID         NOT NULL,
    subject     VARCHAR(100) NOT NULL,
    description VARCHAR     DEFAULT NULL,
    create_date VARCHAR(25) DEFAULT NULL,
    update_date VARCHAR(25) DEFAULT NULL,
    delete_date VARCHAR(25) DEFAULT NULL,
    author      VARCHAR(50) DEFAULT NULL,
    status      VARCHAR(15) DEFAULT NULL,
    user_id     BIGINT       NOT NULL
);

CREATE TABLE IF NOT EXISTS COMMENT
(
    id          BIGSERIAL PRIMARY KEY,
    comment_id  UUID   NOT NULL,
    text        VARCHAR     DEFAULT NULL,
    create_date VARCHAR(25) DEFAULT NULL,
    update_date VARCHAR(25) DEFAULT NULL,
    delete_date VARCHAR(25) DEFAULT NULL,
    author      VARCHAR(50) DEFAULT NULL,
    blog_id     BIGINT NOT NULL,
    user_id     BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(20)  NOT NULL,
    surname      VARCHAR(50)  NOT NULL,
    email        VARCHAR(100) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    zip_code     VARCHAR(10)  NOT NULL,
    phone_number VARCHAR(12)  NOT NULL
);



CREATE TABLE IF NOT EXISTS authority
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_authorities
(
    user_id        BIGINT NOT NULL,
    authorities_id BIGINT NOT NULL
);


CREATE TABLE IF NOT EXISTS file
(
    id                BIGSERIAL PRIMARY KEY,
    file_id           UUID           NOT NULL,
    file_name         VARCHAR(100)   NOT NULL,
    file_extension    VARCHAR(15)    NOT NULL,
    media_type        VARCHAR(20)    NOT NULL,
    size              BIGINT         NOT NULL,
    timestamp         TIMESTAMP      DEFAULT current_timestamp
);