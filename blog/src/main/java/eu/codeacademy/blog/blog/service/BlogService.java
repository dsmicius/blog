package eu.codeacademy.blog.blog.service;

import eu.codeacademy.blog.blog.model.Blog;
import eu.codeacademy.blog.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public void addBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public List<Blog> getBlogs() {
        return blogRepository.getBlogs();
    }

    public Blog getBlogByUUID(UUID id) {
        return blogRepository.getBlogByUUID(id);
    }

    public void updateBlog(Blog blog) {
        blogRepository.update(blog);
    }

    public void deleteBlog(UUID id) {
        blogRepository.delete(id);
    }
}
