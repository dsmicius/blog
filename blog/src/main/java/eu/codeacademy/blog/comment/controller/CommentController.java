package eu.codeacademy.blog.comment.controller;

import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.blog.service.BlogService;
import eu.codeacademy.blog.comment.dto.CommentDto;
import eu.codeacademy.blog.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final BlogService blogService;
    private final CommentService commentService;

    @GetMapping("/{blogId}/comment")
    public String openCommentForm(Model model, @PathVariable("blogId") UUID id) {
        Blog blog = blogService.getBlogByBlogId(id);
        model.addAttribute("comment", CommentDto.builder().build());
        model.addAttribute("blog",blog);
        return "comment/comment";
    }

    @PostMapping("/{blogId}/comment")
    public String createComment(Model model, @PathVariable("blogId") UUID id, CommentDto commentDto) {
        Blog blog = blogService.getBlogByBlogId(id);
        commentService.addComment(commentDto, blog);
        model.addAttribute("comment", CommentDto.builder().build());
        return "redirect:/blogs/list?message=create.comment.message.success";
    }

    @GetMapping("/list")
    public String getComments(Model model) {
        model.addAttribute("comments",commentService.getComments());
        return "comment/comments";
    }

    @GetMapping("/{blogId}/list")
    public String getComments(Model model, @PathVariable("blogId") UUID id) {
        Blog blog = blogService.getBlogByBlogId(id);
        model.addAttribute("comments",commentService.getBlogComments(blog));
        return "comment/comments";
    }

}
