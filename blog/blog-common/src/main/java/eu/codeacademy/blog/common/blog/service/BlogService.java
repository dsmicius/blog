package eu.codeacademy.blog.common.blog.service;

import eu.codeacademy.blog.common.blog.dto.BlogDto;
import eu.codeacademy.blog.common.blog.exception.BlogNotFoundException;
import eu.codeacademy.blog.common.blog.mapper.BlogMapper;
import eu.codeacademy.blog.common.user.dto.UserDto;
import eu.codeacademy.blog.common.user.service.UserService;
import eu.codeacademy.blog.common.utils.CurrentDate;
import eu.codeacademy.blog.jpa.blog.entity.Blog;
import eu.codeacademy.blog.jpa.blog.repository.BlogRepository;
import eu.codeacademy.blog.jpa.user.entity.User;
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
    private final UserService userService;

    public void addBlog(BlogDto blogDto, UserDto userDto) {
        Optional<User> userOptional = userService.getUserEntityByUserName(userDto.getEmail());
        blogRepository.save(Blog.builder()
                .blogId(UUID.randomUUID())
                .subject(blogDto.getSubject())
                .description(blogDto.getDescription())
                .createDate(currentDate.getCurrentDate())
                .updateDate(blogDto.getUpdateDate())
                .deleteDate(blogDto.getDeleteDate())
                .author(blogDto.getAuthor())
                .status(blogDto.getStatus())
                .user(userOptional.get())
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

    public Page<BlogDto> getBlogBySubjectPageable(String subject, Pageable pageable) {
        return blogRepository.findBlogsBySubjectIsLikeIgnoreCase(convertToLikeResult(subject), pageable)
                .map(mapper::mapTo);
    }

    private  String convertToLikeResult(String value) {
        return '%' + value + '%';
    }
}
