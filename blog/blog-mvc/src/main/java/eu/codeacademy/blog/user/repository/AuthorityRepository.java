package eu.codeacademy.blog.user.repository;

import eu.codeacademy.blog.user.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
