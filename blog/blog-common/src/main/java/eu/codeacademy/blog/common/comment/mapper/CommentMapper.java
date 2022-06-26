package eu.codeacademy.blog.common.comment.mapper;

import eu.codeacademy.blog.common.comment.dto.CommentDto;
import eu.codeacademy.blog.common.user.dto.UserDto;
import eu.codeacademy.blog.jpa.comment.entity.Comment;
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
                .userDto(UserDto.builder()
                        .name(comment.getUser().getName())
                        .surname(comment.getUser().getSurname())
                        .phoneNumber(comment.getUser().getPhoneNumber())
                        .email(comment.getUser().getEmail())
                        .build())
                .build();
    }
}
