package eu.codeacademy.blog.common.comment.dto;

import eu.codeacademy.blog.common.user.dto.UserDto;
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
//    private BlogDto blog;
    private UserDto userDto;
}
