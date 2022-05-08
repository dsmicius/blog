CREATE TABLE BLOG (
    id INT PRIMARY KEY,
    blog_id UUID NOT NULL,
    subject VARCHAR(50) NOT NULL,
    description VARCHAR(1000) DEFAULT NULL,
    create_date DATE DEFAULT NULL,
    update_date DATE DEFAULT NULL,
    delete_date DATE DEFAULT NULL,
    author VARCHAR(50) DEFAULT NULL,
    status VARCHAR(15) DEFAULT NULL
);