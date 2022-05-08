package eu.codeacademy.blog.blog.service;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.blog.mapper.BlogMapper;
import eu.codeacademy.blog.blog.repository.BlogRepository;
import eu.codeacademy.blog.utils.CurrentDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final BlogMapper mapper;
    private final CurrentDate currentDate;

    public void addBlog(BlogDto blog) {
        blogRepository.save(Blog.builder()
                .blogId(UUID.randomUUID())
                .subject(blog.getSubject())
                .description(blog.getDescription())
                .createDate(currentDate.getCurrentDate())
                .updateDate(blog.getUpdateDate())
                .deleteDate(blog.getDeleteDate())
                .author(blog.getAuthor())
                .status(blog.getStatus())
                .build());
    }

    public List<BlogDto> getBlogs() {
        return blogRepository.findAll().stream()
                .map(mapper::mapTo)
                .collect(Collectors.toList());
    }

    public BlogDto getBlogByUUID(UUID id) {

        return mapper.mapTo(blogRepository.findByBlogId(id));
    }

    public void updateBlog(BlogDto blog) {

//        blogRepository.update(blog);
    }

    public void deleteBlog(UUID id) {

//        blogRepository.delete(id);
    }
}
