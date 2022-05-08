package eu.codeacademy.blog.blog.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Blog {

    private String subject;
    private String description;
}
