package eu.codeacademy.blog.blog.dto;

import eu.codeacademy.blog.comment.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
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
    private Set<Comment> comments;
}
