package eu.codeacademy.blog.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    @GetMapping("/blogs/open")
    public String openCreateBlogForm() {
        return "blog";
    }

//    @PostMapping("/blogs/open")
//    public String
}
