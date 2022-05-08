package eu.codeacademy.blog.blog.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Blog {
    private UUID uuid;
    private String subject;
    private String description;
}
