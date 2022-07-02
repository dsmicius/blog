package eu.codeacademy.blog.api.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter

public class UserDto {

    private String name;
    private String surname;

    @NotBlank
    @Email
    private String email;

    @Size(min = 6, max = 255)
    private String password;

    @Size(min = 6, max = 255)
    private String repeatedPassword;

    private String zipCode;

    private String phoneNumber;

    public String getFullName() {
        return this.name + " " + this.surname;
    }

}