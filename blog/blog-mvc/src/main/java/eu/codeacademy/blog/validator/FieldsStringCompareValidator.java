package eu.codeacademy.blog.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class FieldsStringCompareValidator implements ConstraintValidator<FieldsStringCompare, Object> {

    private String firstField;
    private String secondField;

    @Override
    public void initialize(FieldsStringCompare constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        try {
            Object firstFieldValue = getValue(obj, firstField);
            Object secondFieldValue = getValue(obj, secondField);

            return firstFieldValue != null && firstFieldValue.equals(secondFieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            //
        }

        return false;
    }

    private Object getValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        return field.get(obj);
    }
}
