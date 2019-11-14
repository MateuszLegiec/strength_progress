package com.strengthprogress.web.backend.validation.validators;


import com.strengthprogress.web.backend.validation.ValidDateOfBirth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, String> {

   public void initialize(ValidDateOfBirth constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return LocalDate.parse(obj).isAfter(LocalDate.now().minusYears(14));
   }
}
