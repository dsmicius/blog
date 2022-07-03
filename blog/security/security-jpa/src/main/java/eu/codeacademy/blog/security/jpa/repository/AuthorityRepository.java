package eu.codeacademy.blog.security.jpa.repository;

import eu.codeacademy.blog.security.jpa.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
