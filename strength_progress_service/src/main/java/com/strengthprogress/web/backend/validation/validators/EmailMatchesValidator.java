package com.strengthprogress.web.backend.validation.validators;


import com.strengthprogress.web.backend.dto.UpdateEmailDTO;
import com.strengthprogress.web.backend.validation.EmailMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailMatchesValidator implements ConstraintValidator<EmailMatches, Object> {

   public void initialize(EmailMatches constraint) {
   }

   @Override
   public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
      UpdateEmailDTO dto = (UpdateEmailDTO) o;
      return dto.getEmail().equals(dto.getRepeatedEmail());
   }

}
