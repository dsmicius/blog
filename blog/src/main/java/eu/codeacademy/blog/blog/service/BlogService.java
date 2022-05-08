package eu.codeacademy.blog.blog.service;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.blog.mapper.BlogMapper;
import eu.codeacademy.blog.blog.repository.BlogRepository;
import eu.codeacademy.blog.utils.CurrentDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final BlogMapper mapper;
    private final CurrentDate currentDate;

    public void addBlog(BlogDto blogDto) {
        blogRepository.save(Blog.builder()
                .blogId(UUID.randomUUID())
                .subject(blogDto.getSubject())
                .description(blogDto.getDescription())
                .createDate(currentDate.getCurrentDate())
                .updateDate(blogDto.getUpdateDate())
                .deleteDate(blogDto.getDeleteDate())
                .author(blogDto.getAuthor())
                .status(blogDto.getStatus())
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

    @Transactional
    public void updateBlog(BlogDto blogDto) {
        Blog blog = blogRepository.findByBlogId(blogDto.getBlogId()).toBuilder()
                .subject(blogDto.getSubject())
                .description(blogDto.getDescription())
                .createDate(currentDate.getCurrentDate())
                .updateDate(blogDto.getUpdateDate())
                .deleteDate(blogDto.getDeleteDate())
                .author(blogDto.getAuthor())
                .status(blogDto.getStatus())
                .build();
        blogRepository.save(blog);
    }

    @Transactional
    public void deleteBlog(UUID id) {
        blogRepository.deleteById(blogRepository.findByBlogId(id).getId());
    }
}
