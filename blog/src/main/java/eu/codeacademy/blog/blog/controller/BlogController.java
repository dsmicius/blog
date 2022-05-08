package eu.codeacademy.blog.blog.controller;

import eu.codeacademy.blog.blog.model.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    @GetMapping("/blogs/open")
    public String openCreateBlogForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog";
    }

    @PostMapping("/blogs/open")
    public String createBlog(Model model, Blog blog) {
        return "blog";
    }
}
