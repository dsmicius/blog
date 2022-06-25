package eu.codeacademy.blog.blog.mapper;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.comment.mapper.CommentMapper;
import eu.codeacademy.blog.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BlogMapper {

    private final CommentMapper commentMapper;

    public BlogDto mapTo(Blog blog) {
        return BlogDto.builder()
                .blogId(blog.getBlogId())
                .subject(blog.getSubject())
                .description(blog.getDescription())
                .createDate(blog.getCreateDate())
                .updateDate(blog.getUpdateDate())
                .deleteDate(blog.getDeleteDate())
                .author(blog.getUser().getName() + " " + blog.getUser().getSurname())
                .status(blog.getStatus())
                .comments(blog.getComments().stream()
                        .map(commentMapper::mapTo)
                        .collect(Collectors.toSet())
                )
                .userDto(UserDto.builder()
                        .name(blog.getUser().getName())
                        .surname(blog.getUser().getSurname())
                        .phoneNumber(blog.getUser().getPhoneNumber())
                        .email(blog.getUser().getEmail())
                        .build())
                .build();
    }
}
