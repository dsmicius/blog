package eu.codeacademy.blog.comment.controller;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.service.BlogService;
import eu.codeacademy.blog.comment.dto.CommentDto;
import eu.codeacademy.blog.comment.exception.CommentDeleteException;
import eu.codeacademy.blog.comment.service.CommentService;
import eu.codeacademy.blog.user.dto.UserRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final BlogService blogService;
    private final CommentService commentService;

    @GetMapping
    public String openCommentForm(Model model, @RequestParam UUID blogId) {
        BlogDto blogDto = blogService.getBlogByUUID(blogId);
        model.addAttribute("comment", CommentDto.builder().build());
        model.addAttribute("blog", blogDto);
        return "comment/comment";
    }

    @PostMapping
    public String createComment(Model model,
                                @RequestParam UUID blogId,
                                CommentDto commentDto,
                                RedirectAttributes redirectAttributes,
                                @AuthenticationPrincipal UserRoleDto userDto) {
        BlogDto blog = blogService.getBlogByUUID(blogId);
        commentService.addComment(commentDto, blog, userDto.getUser());
        model.addAttribute("comment", CommentDto.builder().build());
        redirectAttributes.addFlashAttribute("messageSuccess", "create.comment.message.success");
        return "redirect:/public/blogs/" + blogId + "/view";
    }

    @PostMapping("/delete")
    public String deleteComment(@RequestParam() UUID commentId,
                                RedirectAttributes redirectAttributes,
                                @AuthenticationPrincipal UserRoleDto userDto) {
        try {
            commentService.deleteComment(commentId, userDto.getUser());
            redirectAttributes.addFlashAttribute("messageSuccess", "delete.comment.message.success");
            return "redirect:/public/blogs/list";
        } catch (CommentDeleteException e) {
            redirectAttributes.addFlashAttribute("messageError", e.getMessage());
            return "redirect:/public/blogs/list";
        }
    }
}
