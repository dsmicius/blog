package eu.codeacademy.blog.comment.model;

import eu.codeacademy.blog.blog.dto.BlogDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Comment {

    private UUID commentId;
    private String text;
    private LocalDateTime created;
    private LocalDateTime deleted;
    private LocalDateTime updated;
    private BlogDto blog;
}
