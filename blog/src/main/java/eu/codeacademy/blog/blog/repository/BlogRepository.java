package eu.codeacademy.blog.blog.repository;

import eu.codeacademy.blog.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findByBlogId(UUID id);

    Optional<Blog> findBySubject(String subject);
    Page<Blog> findBlogsBySubjectIsLikeIgnoreCase(String subject, Pageable pageable);

}
