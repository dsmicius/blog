package eu.codeacademy.blog.jpa.user.repository;

import eu.codeacademy.blog.jpa.user.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
