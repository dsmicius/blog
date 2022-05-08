package eu.codeacademy.blog.blog.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
public class Blog {

    @Id
    @GeneratedValue
    private Long id;

    private UUID blogId;
    private String subject;
    private String description;
    private Date createDate;
    private Date updateDate;
    private Date deleteDate;
    private String author;
    private String status;

}
