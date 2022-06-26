package eu.codeacademy.blog.common.validator.spring;

import eu.codeacademy.blog.common.user.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "userRegistrationForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "userRegistrationForm.surname");
    }
}

