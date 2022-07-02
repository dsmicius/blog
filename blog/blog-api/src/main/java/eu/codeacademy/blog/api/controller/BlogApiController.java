package eu.codeacademy.blog.api.controller;

import eu.codeacademy.blog.api.dto.BlogsResponse;
import eu.codeacademy.blog.common.blog.dto.BlogDto;
import eu.codeacademy.blog.common.blog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/page")
    @ApiOperation(value = "Get Blogs Pagable")
    public Page<BlogDto> getBlogsPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        return blogService.getBlogPaginated(PageRequest.of(page, size));
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
    @ApiOperation(value = "delete Blog", httpMethod = "DELETE")
    public void deleteBlog(@PathVariable("uuid") UUID blogId) {
        blogService.deleteBlog(blogId);
    }

    // TODO: reikia kazka sugalvoti del requestBody perdavimo, nes negalima perduoti dvieju objektu. Vadinasi Useris turi ateiti gal kaip parametras?
    @PostMapping
    @ApiOperation(value = "create Blog", httpMethod = "POST")
    public ResponseEntity<Void> createBlog(@Valid @RequestBody BlogDto blogDto, @RequestParam String email) {
        blogService.addBlog(blogDto, email);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @ApiOperation(value = "update Blog", httpMethod = "PUT")
    public ResponseEntity<Void> updateBlog(@Valid @RequestBody BlogDto blogDto) {
        if (blogService.updateBlog(blogDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.notFound().build();
    }
}
