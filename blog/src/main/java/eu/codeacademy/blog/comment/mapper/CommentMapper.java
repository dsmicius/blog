package eu.codeacademy.blog.comment.mapper;

import eu.codeacademy.blog.comment.dto.CommentDto;
import eu.codeacademy.blog.comment.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDto mapTo(Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .text(comment.getText())
                .createDate(comment.getCreateDate())
                .updateDate(comment.getUpdateDate())
                .deleteDate(comment.getDeleteDate())
                .author(comment.getAuthor())
                .blog(comment.getBlog())
                .build();
    }
}
