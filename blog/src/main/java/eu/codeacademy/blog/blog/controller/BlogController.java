package eu.codeacademy.blog.blog.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.helper.MessageService;
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
    private final MessageService messageService;

    @GetMapping
    public String openCreateBlogForm(Model model) {
        model.addAttribute("blog", BlogDto.builder().build());
        return "blog";
    }

    @PostMapping
    public String createBlog(Model model, BlogDto blog) {
        blogService.addBlog(blog);
        model.addAttribute("blog", BlogDto.builder().build());
        return "redirect:/blogs/list?message=create.blog.message.success";
    }

    @GetMapping("/list")
    public String getBlogs(Model model, @PageableDefault(size = 8, sort = {"createDate"}, direction = Sort.Direction.DESC) Pageable pageable, String message) {
        model.addAttribute("blogPage", blogService.getBlogPaginated(pageable));
        model.addAttribute("message",messageService.getMessage(message));
        return "blogs2";
    }

    @GetMapping("/{blogId}/update")
    public String getUpdateBlog(Model model, @PathVariable("blogId") UUID id) {
        model.addAttribute("blog",blogService.getBlogByUUID(id));
        return "blog_update";
    }

    @PostMapping("/{blogId}/update")
    public String updateBlog(Model model, BlogDto blog) {
        blogService.updateBlog(blog);
        return "redirect:/blogs/list";
    }

    @GetMapping("/{blogId}/delete")
    public String deleteBlog(Model model, @PathVariable("blogId") UUID id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs/list";
    }
}
