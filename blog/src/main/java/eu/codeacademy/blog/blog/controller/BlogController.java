package eu.codeacademy.blog.blog.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public String openCreateBlogForm(Model model) {
        model.addAttribute("blog", BlogDto.builder().build());
        return "blog";
    }

    @PostMapping
    public String createBlog(Model model, BlogDto blog) {
        blogService.addBlog(blog);
        model.addAttribute("blog", BlogDto.builder().build());
        model.addAttribute("message","Blog entry " + blog.getSubject() + " added success");
        return "blog";
    }

    @GetMapping("/list")
    public String getBlogs(Model model, @PageableDefault(size = 4, sort = {"createDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("blogPage", blogService.getBlogPaginated(pageable));
        return "blogs2";
    }

    @GetMapping("/{blogId}/update")
    public String getUpdateBlog(Model model, @PathVariable("blogId") UUID id) {
        model.addAttribute("blog",blogService.getBlogByUUID(id));
        return "blog";
    }

    @PostMapping("/{blogId}/update")
    public String updateBlog(Model model, BlogDto blog) {
        blogService.updateBlog(blog);
        model.addAttribute("blogList", blogService.getBlogs());
        return "blogs2";
    }

    @GetMapping("/{blogId}/delete")
    public String deleteBlog(Model model, @PathVariable("blogId") UUID id) {
        blogService.deleteBlog(id);
        model.addAttribute("blogList",blogService.getBlogs());
        return "blogs2";
    }
}
