package eu.codeacademy.blog.blog.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class BlogDto {
    private UUID blogId;
    private String subject;
    private String description;
    private Date createDate;
    private Date updateDate;
    private Date deleteDate;
    private String author;
    private String status;
}
