package eu.codeacademy.blog.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BlogsResponse {

    @ApiModelProperty(notes = "Blogs list", required = true, allowEmptyValue = false)
    private List<BlogDto> blogs;
}
