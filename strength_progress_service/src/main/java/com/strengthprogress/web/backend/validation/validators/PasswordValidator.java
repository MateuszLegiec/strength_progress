package com.strengthprogress.web.backend.validation.validators;

import com.strengthprogress.web.backend.validation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

   private Pattern pattern;
   private Matcher matcher;

   private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";

   public void initialize(ValidPassword constraint) {
   }

   public boolean isValid(String password, ConstraintValidatorContext context) {
      pattern = Pattern.compile(PASSWORD_PATTERN);
      matcher = pattern.matcher(password);
      return matcher.matches();
   }
}
