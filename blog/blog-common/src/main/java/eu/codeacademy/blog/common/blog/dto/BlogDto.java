package eu.codeacademy.blog.common.blog.dto;

import eu.codeacademy.blog.common.comment.dto.CommentDto;
import eu.codeacademy.blog.common.user.dto.UserDto;
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

    @NotBlank(message = "Subject is required")
    @Size(
            min = 3,
            max = 50,
            message = "Minimum 3"
    )
    private String subject;

    @NotBlank(message = "Description is required")
    @Size(
            min = 3,
            message = "Minimum 3"
    )
    private String description;

    private String createDate;
    private String updateDate;
    private String deleteDate;
    private String author;
    private String status;
    private Set<CommentDto> comments;
    private UserDto userDto;
}
