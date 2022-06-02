package eu.codeacademy.blog.blog.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.blog.service.BlogService;
import eu.codeacademy.blog.comment.service.CommentService;
import eu.codeacademy.blog.helper.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {
    private final BlogService blogService;
    private final MessageService messageService;
    private final CommentService commentService;

    @GetMapping
    public String openCreateBlogForm(Model model) {
        model.addAttribute("blog", BlogDto.builder().build());
        return "blog/blog";
    }

    @PostMapping
    public String createBlog(Model model, @Valid BlogDto blog, RedirectAttributes redirectAttributes) {
        blogService.addBlog(blog);
        model.addAttribute("blog", BlogDto.builder().build());
        redirectAttributes.addFlashAttribute("messageSuccess", "create.blog.message.success");
        return "redirect:/blogs/list";
    }

    @GetMapping("/list")
    public String getBlogs(Model model, @PageableDefault(size = 8, sort = {"createDate"}, direction = Sort.Direction.DESC) Pageable pageable, String message) {
        model.addAttribute("blogPage", blogService.getBlogPaginated(pageable));
        model.addAttribute("message", messageService.getMessage(message));
        return "blog/blogs";
    }

    @GetMapping("/update")
    public String getUpdateBlog(Model model, @RequestParam UUID blogId) {
        model.addAttribute("blog", blogService.getBlogByUUID(blogId));
        return "blog/blog_update";
    }

    @GetMapping("/{blogId}/view")
    public String getViewBlog(Model model, @PathVariable("blogId") UUID id) {
        Blog blog = blogService.getBlogByBlogId(id);
        model.addAttribute("blog", blogService.getBlogByUUID(id));
        model.addAttribute("comments", commentService.getBlogComments(blog));
        return "blog/blog_view";
    }

    @PostMapping("/update")
    public String updateBlog(BlogDto blog, RedirectAttributes redirectAttributes) {
        blogService.updateBlog(blog);
        redirectAttributes.addFlashAttribute("messageSuccess", "update.blog.message.success");
        return "redirect:/blogs/list";
    }

    @PostMapping("/delete")
    public String deleteBlog(@RequestParam UUID blogId, RedirectAttributes redirectAttributes) {
        blogService.deleteBlog(blogId);
        redirectAttributes.addFlashAttribute("messageSuccess", "delete.blog.message.success");
        return "redirect:/blogs/list";
    }

}
