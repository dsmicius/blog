package eu.codeacademy.blog.jpa.comment.repository;

import eu.codeacademy.blog.jpa.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentByCommentId(UUID commentId);
}
