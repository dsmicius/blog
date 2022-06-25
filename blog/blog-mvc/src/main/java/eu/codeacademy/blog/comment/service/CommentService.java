package eu.codeacademy.blog.comment.service;

import eu.codeacademy.blog.blog.dto.BlogDto;
import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.blog.service.BlogService;
import eu.codeacademy.blog.comment.dto.CommentDto;
import eu.codeacademy.blog.comment.entity.Comment;
import eu.codeacademy.blog.comment.exception.CommentDeleteException;
import eu.codeacademy.blog.comment.mapper.CommentMapper;
import eu.codeacademy.blog.comment.repository.CommentRepository;
import eu.codeacademy.blog.user.dto.UserDto;
import eu.codeacademy.blog.user.entity.Authority;
import eu.codeacademy.blog.user.entity.User;
import eu.codeacademy.blog.user.service.UserService;
import eu.codeacademy.blog.utils.CurrentDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CurrentDate currentDate;
    private final BlogService blogService;
    private final UserService userService;

    public void addComment(CommentDto commentDto, BlogDto blogDto, UserDto userDto) {
        Blog blog = blogService.getBlogByBlogId(blogDto.getBlogId());
        Optional<User> userOptional = userService.getUserEntityByUserName(userDto.getEmail());
        commentRepository.save(Comment.builder()
                .commentId(UUID.randomUUID())
                .text(commentDto.getText())
                .createDate(currentDate.getCurrentDate())
                .updateDate(commentDto.getUpdateDate())
                .deleteDate(commentDto.getDeleteDate())
                .author(commentDto.getAuthor())
                .blog(blog)
                .user(userOptional.get())
                .build());
    }

    public List<CommentDto> getComments() {
        return commentRepository.findAll().stream()
                .map(commentMapper::mapTo)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getBlogComments(Blog blog) {
        return commentRepository.findAll().stream()
                .filter(c -> c.getBlog().equals(blog))
                .map(commentMapper::mapTo)
                .collect(Collectors.toList());
    }

    public void deleteComment(UUID commentId, UserDto userDto) throws CommentDeleteException {
        Optional<Comment> comment = commentRepository.findCommentByCommentId(commentId);
        Optional<User> userOptional = userService.getUserEntityByUserName(userDto.getEmail());
        Optional<Authority> authorityAdmin = userOptional.get().getAuthorities().stream().filter(a -> a.getName().equals("ADMIN")).findAny();
        if (comment.isPresent() && userOptional.isPresent() && (isUserEquals(comment, userOptional) || authorityAdmin.isPresent())){
            commentRepository.delete(comment.get());
        }else {
            throw new CommentDeleteException("delete.comment.message.error");
        }
    }

    private boolean isUserEquals(Optional<Comment> comment, Optional<User> userOptional) {
        return comment.get().getUser().equals(userOptional.get());
    }


}
