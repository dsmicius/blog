package eu.codeacademy.blog.security.basic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalUser {

    private String username;
    private String password;
    private String[] roles;
}