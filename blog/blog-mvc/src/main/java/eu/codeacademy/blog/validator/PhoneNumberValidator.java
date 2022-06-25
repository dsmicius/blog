package eu.codeacademy.blog.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private PhoneType type;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        this.type = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return type.equals(PhoneType.GLOBAL) ? isValidPrefixGlobal(value) : isValidPrefixLocal(value);
    }

    private boolean isValidPrefixGlobal(String phoneNumber) {
        return phoneNumber != null &&
                (phoneNumber.length() == 12 && phoneNumber.startsWith(PhoneType.GLOBAL.getPrefixCode()));
    }

    private boolean isValidPrefixLocal(String phoneNumber) {
        return phoneNumber != null &&
                (phoneNumber.length() == 9 && phoneNumber.startsWith(PhoneType.LOCAL.getPrefixCode()));
    }
}
