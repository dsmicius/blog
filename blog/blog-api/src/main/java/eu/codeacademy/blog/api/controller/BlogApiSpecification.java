package eu.codeacademy.blog.api.controller;

import eu.codeacademy.blog.api.dto.BlogsResponse;
import eu.codeacademy.blog.api.dto.BlogDto;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping(BlogApiSpecification.BLOG_API_ROOT_PATH)
@Api(tags = "Blog Controller")
public interface BlogApiSpecification {

    String UUID_PATH = "/{uuid}";
    String BLOG_API_ROOT_PATH = "/blogs";


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(
            value = "Get all blogs",
            notes = "Get all blogs from db, and any other information could be here")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kai sėkmingai gražina blog įrašus"),
            @ApiResponse(code = 401, message = "Reikalauja prisijungimo gaunant blog sąrašą"),
            @ApiResponse(code = 403, message = "Neturite reikalingų teisių gauti blog sąrašą")
    }
    )
    public BlogsResponse getBlogs();

    @GetMapping("/page")
    @ApiOperation(value = "Get all Blogs by page and size")
    public Page<BlogDto> getBlogsPaginated(@ApiParam(
            name = "page",
            type = "int",
            value = "Number of page",
            example = "1",
            required = true)
            @RequestParam("page") int page,

            @ApiParam(
            name = "size",
            type = "int",
            value = "Content size in page",
            example = "1",
            required = true)
            @RequestParam("size") int size);

    @GetMapping(
            path = UUID_PATH,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Get Blog by uuid", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Kai sėkmingai gražina Blog įrašą"),
            @ApiResponse(code = 401, message = "Reikalauja prisijungimo gaunant Blog įrašą"),
            @ApiResponse(code = 403, message = "Neturite reikalingų teisių gauti Blog įrašą")
    }
    )
    public BlogsResponse getBlogs(@PathVariable("uuid") UUID uuid);

    @DeleteMapping(
            path = UUID_PATH)
    @ApiOperation(value = "delete Blog", httpMethod = "DELETE")
    public void deleteBlog(@PathVariable("uuid") UUID blogId);

    // TODO: reikia kazka sugalvoti del requestBody perdavimo, nes negalima perduoti dvieju objektu. Vadinasi Useris turi ateiti gal kaip parametras?
    @PostMapping
    @ApiOperation(value = "create Blog", httpMethod = "POST")
    public ResponseEntity<Void> createBlog(@Valid @RequestBody BlogDto blogDto, @RequestParam String email);

    @PutMapping
    @ApiOperation(value = "update Blog", httpMethod = "PUT")
    public ResponseEntity<Void> updateBlog(@Valid @RequestBody BlogDto blogDto);
}
