package eu.codeacademy.blog.comment.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.comment.dto.CommentDto;
import eu.codeacademy.blog.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public String openCommentForm(Model model) {
        model.addAttribute("comment", CommentDto.builder().build());
        model.addAttribute("blog", BlogDto.builder().build());
        return "comment";
    }

    @PostMapping
    public String createComment(Model model, CommentDto commentDto, BlogDto blogDto) {
        commentService.addComment(commentDto);
        model.addAttribute("comment", CommentDto.builder().build());
        model.addAttribute("blog", BlogDto.builder().build());
        return "comment";
    }
}
