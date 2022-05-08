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
    private String createDate;
    private String updateDate;
    private String deleteDate;
    private String author;
    private String status;
}
