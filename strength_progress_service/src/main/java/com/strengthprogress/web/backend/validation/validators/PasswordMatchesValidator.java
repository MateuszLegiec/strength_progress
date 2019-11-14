package com.strengthprogress.web.backend.validation.validators;

import com.strengthprogress.web.backend.util.PasswordMatchable;
import com.strengthprogress.web.backend.validation.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        final PasswordMatchable dto = (PasswordMatchable) o;
        return dto.getPassword().equals(dto.getRepeatedPassword());
    }
}
