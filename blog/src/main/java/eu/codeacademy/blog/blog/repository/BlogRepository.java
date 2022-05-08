package eu.codeacademy.blog.blog.repository;


import eu.codeacademy.blog.blog.model.Blog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BlogRepository {
    private final List<Blog> blogs = new ArrayList<>();

    public void save(Blog blog) {
        blogs.add(blog);
    }

    public List<Blog> getBlogs() {
        return blogs;
    }
}
