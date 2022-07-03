package eu.codeacademy.blog.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter

public class UserDto {

    private String name;
    private String surname;

    private String email;


    private String password;

    private String repeatedPassword;

    private String zipCode;

    private String phoneNumber;

    public String getFullName() {
        return this.name + " " + this.surname;
    }

}