package eu.codeacademy.blog.api.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ApiExceptionResponse {

    private final String message;
    private final int status;

    @Builder.Default
    private final String timestamp = LocalDateTime.now().toString();
}
