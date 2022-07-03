package eu.codeacademy.blog.security.jwt.controller;

import eu.codeacademy.blog.commons.swagger.annotation.OpenApi;
import eu.codeacademy.blog.security.jwt.dto.LoginResponse;
import eu.codeacademy.blog.security.jwt.dto.UserRoleDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@OpenApi
public class LoginController {

    @PostMapping
    public LoginResponse login(@AuthenticationPrincipal UserRoleDto userRoleDto) {
        return new LoginResponse(userRoleDto);
    }
}
