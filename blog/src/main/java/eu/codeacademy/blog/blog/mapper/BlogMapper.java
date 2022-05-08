package eu.codeacademy.blog.blog.mapper;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.entity.Blog;
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
                .author(blog.getAuthor())
                .status(blog.getStatus())
                .build();
    }
}
