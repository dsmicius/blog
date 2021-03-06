package eu.codeacademy.blog.blog.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class BlogNotFoundException extends RuntimeException {

    private final UUID blogId;

}
