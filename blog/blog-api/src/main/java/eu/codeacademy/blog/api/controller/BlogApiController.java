package eu.codeacademy.blog.api.controller;

import eu.codeacademy.blog.api.dto.BlogsResponse;
import eu.codeacademy.blog.api.dto.BlogDto;
import eu.codeacademy.blog.api.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BlogApiController implements BlogApiSpecification{

    private final BlogService blogService;

    public BlogsResponse getBlogs() {
        return BlogsResponse.builder().blogs(blogService.getBlogs()).build();
    }

    public Page<BlogDto> getBlogsPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        return blogService.getBlogPaginated(PageRequest.of(page, size));
    }
    public BlogsResponse getBlogs(@PathVariable("uuid") UUID uuid) {
        return BlogsResponse.builder()
                .blogs(List.of(blogService.getBlogByUUID(uuid)))
                .build();
    }

    public void deleteBlog(@PathVariable("uuid") UUID blogId) {
        blogService.deleteBlog(blogId);
    }
    public ResponseEntity<Void> createBlog(@Valid @RequestBody BlogDto blogDto, @RequestParam String email) {
        blogService.addBlog(blogDto, email);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Void> updateBlog(@Valid @RequestBody BlogDto blogDto) {
        if (blogService.updateBlog(blogDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.notFound().build();
    }
}
