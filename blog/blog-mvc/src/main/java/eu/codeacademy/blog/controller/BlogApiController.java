package eu.codeacademy.blog.controller;

import eu.codeacademy.blog.common.blog.dto.BlogDto;
import eu.codeacademy.blog.common.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<BlogDto> getJsonBlogs() {
        return blogService.getBlogs();
    }
}
