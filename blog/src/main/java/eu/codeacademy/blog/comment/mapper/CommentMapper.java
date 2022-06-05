package eu.codeacademy.blog.comment.mapper;

import eu.codeacademy.blog.comment.dto.CommentDto;
import eu.codeacademy.blog.comment.entity.Comment;
import eu.codeacademy.blog.user.dto.UserDto;
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
                .author(comment.getUser().getName() + " " + comment.getUser().getSurname())
                .blog(comment.getBlog())
                .userDto(UserDto.builder()
                        .name(comment.getUser().getName())
                        .surname(comment.getUser().getSurname())
                        .phoneNumber(comment.getUser().getPhoneNumber())
                        .email(comment.getUser().getEmail())
                        .build())
                .build();
    }
}
