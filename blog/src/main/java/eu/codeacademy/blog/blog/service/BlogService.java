package eu.codeacademy.blog.blog.service;

import eu.codeacademy.blog.blog.model.Blog;
import eu.codeacademy.blog.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public void addBlog(Blog blog) {
        blogRepository.save(blog);
    }
}
