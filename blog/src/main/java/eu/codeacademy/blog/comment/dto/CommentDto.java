package eu.codeacademy.blog.comment.dto;

import eu.codeacademy.blog.blog.entity.Blog;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CommentDto {
    private UUID commentId;
    private String text;
    private String createDate;
    private String updateDate;
    private String deleteDate;
    private String author;
    private Blog blog;
}
