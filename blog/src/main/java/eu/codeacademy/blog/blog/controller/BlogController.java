package eu.codeacademy.blog.blog.controller;

import eu.codeacademy.blog.blog.model.Blog;
import eu.codeacademy.blog.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public String openCreateBlogForm(Model model) {
        model.addAttribute("blog", Blog.builder().build());
        return "blog";
    }

    @PostMapping
    public String createBlog(Model model, Blog blog) {
        blogService.addBlog(blog);
        return "home";
    }

    @GetMapping("/list")
    public String getBlogs(Model model) {
        model.addAttribute("blogList", blogService.getBlogs());
        return "blogs";
    }

    @GetMapping("/update")
    public String getUpdateBlog(Model model, @RequestParam UUID id) {
        model.addAttribute("blog",blogService.getBlogByUUID(id));
        return "blog";
    }

    @PostMapping("/update")
    public String updateBlog(Model model, Blog blog) {
        blogService.updateBlog(blog);
        model.addAttribute("blogList", blogService.getBlogs());
        return "blogs";
    }

    @GetMapping("/delete")
    public String deleteBlog(Model model, @RequestParam UUID id) {
        blogService.deleteBlog(id);
        model.addAttribute("blogList",blogService.getBlogs());
        return "blogs";
    }
}
