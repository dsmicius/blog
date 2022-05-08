package eu.codeacademy.blog.blog.repository;


import eu.codeacademy.blog.blog.model.Blog;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BlogRepository {
    private final Map<UUID, Blog> blogs = new HashMap<>();

    public void save(Blog blog) {
        blog.setBlogId(UUID.randomUUID());
        blogs.put(blog.getBlogId(), blog);
    }

    public List<Blog> getBlogs() {
        return new ArrayList<>(blogs.values());
    }

    public Blog getBlogByUUID(UUID id) {
        return blogs.get(id);
    }

    public void update(Blog blog) {
        blogs.put(blog.getBlogId(),blog);
    }

    public void delete(UUID id) {
        blogs.remove(id);
    }
}
