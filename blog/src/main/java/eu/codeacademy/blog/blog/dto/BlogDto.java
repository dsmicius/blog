package eu.codeacademy.blog.blog.dto;

import eu.codeacademy.blog.comment.entity.Comment;
import eu.codeacademy.blog.user.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class BlogDto {
    private UUID blogId;
    @NotBlank
    @Size(
            min = 3,
            max = 100
    )
    private String subject;
    @NotBlank
    private String description;
    private String createDate;
    private String updateDate;
    private String deleteDate;
    private String author;
    private String status;
    private Set<Comment> comments;
    private UserDto userDto;
}
