package eu.codeacademy.blog.validator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PhoneType {
    GLOBAL("+370"),
    LOCAL("8");

    private final String prefixCode;
}