package eu.codeacademy.blog.comment.repository;

import eu.codeacademy.blog.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
//    Optional<Comment> findCommentBy(UUID id);
}
