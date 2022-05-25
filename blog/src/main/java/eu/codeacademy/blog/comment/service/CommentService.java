package eu.codeacademy.blog.comment.service;

import eu.codeacademy.blog.blog.entity.Blog;
import eu.codeacademy.blog.comment.dto.CommentDto;
import eu.codeacademy.blog.comment.entity.Comment;
import eu.codeacademy.blog.comment.mapper.CommentMapper;
import eu.codeacademy.blog.comment.repository.CommentRepository;
import eu.codeacademy.blog.utils.CurrentDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CurrentDate currentDate;

    public void addComment(CommentDto commentDto, Blog blog) {
        commentRepository.save(Comment.builder()
                .commentId(UUID.randomUUID())
                .text(commentDto.getText())
                .createDate(currentDate.getCurrentDate())
                .updateDate(commentDto.getUpdateDate())
                .deleteDate(commentDto.getDeleteDate())
                .author(commentDto.getAuthor())
                .blog(blog)
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
}
