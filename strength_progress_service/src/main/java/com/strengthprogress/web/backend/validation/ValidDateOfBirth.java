package com.strengthprogress.web.backend.validation;

import com.strengthprogress.web.backend.validation.validators.DateOfBirthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DateOfBirthValidator.class)
@Documented
public @interface ValidDateOfBirth {

    String message() default "User should have at least 14 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
