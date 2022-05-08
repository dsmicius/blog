package eu.codeacademy.blog.blog.controller;

import eu.codeacademy.blog.blog.model.Blog;
import eu.codeacademy.blog.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/blogs/open")
    public String openCreateBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog";
    }

    @PostMapping("/blogs/open")
    public String createBlog(Model model, Blog blog) {
        blogService.addBlog(blog);
        return "blog";
    }
}
