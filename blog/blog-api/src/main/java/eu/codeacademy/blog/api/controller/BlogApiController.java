package eu.codeacademy.blog.api.controller;

import eu.codeacademy.blog.api.dto.BlogsResponse;
import eu.codeacademy.blog.common.blog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blogs")
@Api(tags = "Blog Controller")
public class BlogApiController {

    private final BlogService blogService;

    @GetMapping(produces = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
            value = "Get all blogs",
            notes = "Get all blogs from db, and any other information could be here"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kai sėkmingai gražina blog įrašus"),
            @ApiResponse(code = 401, message = "Reikalauja prisijungimo gaunant blog sąrašą"),
            @ApiResponse(code = 403, message = "Neturite reikalingų teisių gauti blog sąrašą")
    }
    )
    public BlogsResponse getBlogs() {
        return BlogsResponse.builder().blogs(blogService.getBlogs()).build();
    }
}