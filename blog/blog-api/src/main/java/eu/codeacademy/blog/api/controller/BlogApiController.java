package eu.codeacademy.blog.api.controller;

import eu.codeacademy.blog.api.dto.BlogsResponse;
import eu.codeacademy.blog.common.blog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(BlogApiController.BLOG_API_ROOT_PATH)
@Api(tags = "Blog Controller")
public class BlogApiController {

    private static final String UUID_PATH = "/{uuid}";
    public static final String BLOG_API_ROOT_PATH = "/api/blogs";

    private final BlogService blogService;

    @GetMapping(produces = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
            value = "Get all blogs",
            notes = "Get all blogs from db, and any other information could be here")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kai sėkmingai gražina blog įrašus"),
            @ApiResponse(code = 401, message = "Reikalauja prisijungimo gaunant blog sąrašą"),
            @ApiResponse(code = 403, message = "Neturite reikalingų teisių gauti blog sąrašą")
    }
    )
    public BlogsResponse getBlogs() {

        return BlogsResponse.builder().blogs(blogService.getBlogs()).build();
    }

    @GetMapping(
            path = UUID_PATH,
            produces = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Get Blog by uuid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kai sėkmingai gražina Blog įrašą"),
            @ApiResponse(code = 401, message = "Reikalauja prisijungimo gaunant Blog įrašą"),
            @ApiResponse(code = 403, message = "Neturite reikalingų teisių gauti Blog įrašą")
    }
    )
    public BlogsResponse getBlogs(@PathVariable("uuid") UUID uuid) {
        return BlogsResponse.builder()
                .blogs(List.of(blogService.getBlogByUUID(uuid)))
                .build();
    }

    @DeleteMapping(
            path = UUID_PATH)
    public void deleteBlog(@PathVariable("uuid") UUID blogId) {
        blogService.deleteBlog(blogId);
    }
}
