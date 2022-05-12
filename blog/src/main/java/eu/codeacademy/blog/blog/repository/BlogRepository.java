package eu.codeacademy.blog.blog.repository;

import eu.codeacademy.blog.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Blog findByBlogId(UUID id);

}