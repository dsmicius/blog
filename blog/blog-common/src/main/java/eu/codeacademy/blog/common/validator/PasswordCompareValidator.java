package eu.codeacademy.blog.common.validator;

import eu.codeacademy.blog.common.user.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordCompareValidator implements ConstraintValidator<PasswordCompare, UserDto> {

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        return userDto.getPassword() != null
                && !userDto.getPassword().equals("")
                && userDto.getPassword().equals(userDto.getRepeatedPassword());
    }
}
