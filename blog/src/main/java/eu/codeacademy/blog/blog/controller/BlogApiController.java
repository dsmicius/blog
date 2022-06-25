package eu.codeacademy.blog.blog.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/blogs")
public class BlogApiController {

    private final BlogService blogService;

    @GetMapping
    @ResponseBody
    public List<BlogDto> getBlogs() {
        return blogService.getBlogs();
    }
}
