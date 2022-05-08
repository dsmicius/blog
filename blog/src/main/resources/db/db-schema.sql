CREATE TABLE BLOG (
    id INT PRIMARY KEY AUTO_INCREMENT,
    blog_id UUID NOT NULL,
    subject VARCHAR(50) NOT NULL,
    description VARCHAR(1000) DEFAULT NULL,
    create_date VARCHAR(25) DEFAULT NULL,
    update_date VARCHAR(25) DEFAULT NULL,
    delete_date VARCHAR(25) DEFAULT NULL,
    author VARCHAR(50) DEFAULT NULL,
    status VARCHAR(15) DEFAULT NULL
);