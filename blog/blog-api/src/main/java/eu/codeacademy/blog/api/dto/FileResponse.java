package eu.codeacademy.blog.api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FileResponse {

    private String originalFileName;
}
