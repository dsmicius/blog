package eu.codeacademy.blog.blog.config;

import eu.codeacademy.blog.blog.model.Blog;
import eu.codeacademy.blog.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class MockDataConfig {

    private static final int MAX_COUNT = 10;
    private final BlogRepository blogRepository;

    @Bean
    public void initBlogs() {
        int counter = 0;
        while (MAX_COUNT >= counter) {
            blogRepository.save(Blog.builder()
                    .subject("Trumpas aprasymas Nr. " + counter)
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Est id sapiente totam! Ad eum expedita impedit soluta vero voluptas! Aliquam deserunt earum magnam molestiae nemo omnis quisquam reprehenderit totam vero.")
                    .build());
            counter++;
        }
    }
}
