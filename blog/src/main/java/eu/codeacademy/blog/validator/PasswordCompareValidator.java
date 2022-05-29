package eu.codeacademy.blog.validator;

import eu.codeacademy.blog.user.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordCompareValidator implements ConstraintValidator<PasswordCompare, UserDto> {

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        return !userDto.getPassword().equals("") &&
                userDto.getPassword().equals(userDto.getRepeatedPassword());
    }
}
