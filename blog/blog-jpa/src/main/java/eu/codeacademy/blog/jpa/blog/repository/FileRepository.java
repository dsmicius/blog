package eu.codeacademy.blog.jpa.blog.repository;

import eu.codeacademy.blog.jpa.file.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
