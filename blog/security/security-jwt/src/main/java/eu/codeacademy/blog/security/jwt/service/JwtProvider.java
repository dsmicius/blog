package eu.codeacademy.blog.security.jwt.service;

import eu.codeacademy.blog.security.jwt.dto.UserRoleDto;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    public String getToken(UserRoleDto principal) {
        return "My token: " + principal.getFullName();
    }
}
