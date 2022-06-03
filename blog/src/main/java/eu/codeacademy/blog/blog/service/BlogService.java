package eu.codeacademy.blog.blog.service;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.blog.exception.BlogNotFoundException;
import eu.codeacademy.blog.blog.mapper.BlogMapper;
import eu.codeacademy.blog.blog.repository.BlogRepository;
import eu.codeacademy.blog.utils.CurrentDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public Page<BlogDto> getBlogPaginated(Pageable pageable) {
        return blogRepository.findAll(pageable)
                .map(mapper::mapTo);
    }

    public List<BlogDto> getBlogs() {
        return blogRepository.findAll().stream()
                .map(mapper::mapTo)
                .collect(Collectors.toList());
    }

    public BlogDto getBlogByUUID(UUID id) {
        return blogRepository.findByBlogId(id)
                .map(mapper::mapTo)
                .orElseThrow(() -> new BlogNotFoundException(id));
    }

    @Transactional
    public void updateBlog(BlogDto blogDto) {
        Optional<Blog> blogOptional = blogRepository.findByBlogId(blogDto.getBlogId());
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get().toBuilder()
                    .subject(blogDto.getSubject())
                    .description(blogDto.getDescription())
                    .createDate(currentDate.getCurrentDate())
                    .updateDate(currentDate.getCurrentDate())
                    .deleteDate(blogDto.getDeleteDate())
                    .author(blogDto.getAuthor())
                    .status(blogDto.getStatus())
                    .build();
            blogRepository.save(blog);
        }
    }

    @Transactional
    public void deleteBlog(UUID id) {
        Optional<Blog> blog = blogRepository.findByBlogId(id);
        if (blog.isPresent()) {
            blogRepository.deleteById(blog.get().getId());
        }
    }

    public Blog getBlogByBlogId(UUID id) {
        Optional<Blog> blog = blogRepository.findByBlogId(id);
        return blog.get();
    }
}
