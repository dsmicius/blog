package eu.codeacademy.blog.blog.mapper;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {

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
                .comments(blog.getComments())
                .userDto(UserDto.builder()
                        .name(blog.getUser().getName())
                        .surname(blog.getUser().getSurname())
                        .phoneNumber(blog.getUser().getPhoneNumber())
                        .email(blog.getUser().getEmail())
                        .build())
                .build();
    }
}
