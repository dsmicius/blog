package eu.codeacademy.blog.user.dto;

import eu.codeacademy.blog.validator.PasswordCompare;
import eu.codeacademy.blog.validator.PhoneNumber;
import eu.codeacademy.blog.validator.PhoneType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@PasswordCompare
public class UserDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 255)
    private String password;

    @NotBlank
    @Size(min = 6, max = 255)
    private String repeatedPassword;

    @NotBlank
    private String zipCode;

    @PhoneNumber(type = PhoneType.LOCAL)
    private String phoneNumber;
}
