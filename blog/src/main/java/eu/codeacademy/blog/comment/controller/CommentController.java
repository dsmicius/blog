package eu.codeacademy.blog.comment.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.blog.service.BlogService;
import eu.codeacademy.blog.comment.dto.CommentDto;
import eu.codeacademy.blog.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final BlogService blogService;
    private final CommentService commentService;

    @GetMapping()
    public String openCommentForm(Model model, @RequestParam UUID blogId) {
        BlogDto blogDto = blogService.getBlogByUUID(blogId);
        model.addAttribute("comment", CommentDto.builder().build());
        model.addAttribute("blog",blogDto);
        return "comment/comment";
    }

    @PostMapping
    public String createComment(Model model, @RequestParam UUID blogId, CommentDto commentDto, RedirectAttributes redirectAttributes) {
        BlogDto blog = blogService.getBlogByUUID(blogId);
        commentService.addComment(commentDto, blog);
        model.addAttribute("comment", CommentDto.builder().build());
        redirectAttributes.addFlashAttribute("messageSuccess", "create.comment.message.success");
        return "redirect:/public/blogs/list";
    }

    @GetMapping("/{blogId}/list")
    public String getComments(Model model, @PathVariable("blogId") UUID id) {
        Blog blog = blogService.getBlogByBlogId(id);
        model.addAttribute("comments",commentService.getBlogComments(blog));
        return "comment/comments";
    }
}
