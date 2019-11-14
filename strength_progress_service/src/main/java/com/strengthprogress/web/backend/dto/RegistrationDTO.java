package com.strengthprogress.web.backend.dto;

import com.strengthprogress.web.backend.util.PasswordMatchable;
import com.strengthprogress.web.backend.validation.PasswordMatches;
import com.strengthprogress.web.backend.validation.ValidDateOfBirth;
import com.strengthprogress.web.backend.validation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class RegistrationDTO implements PasswordMatchable {

    @Email
    private String email;
    @ValidPassword
    private String password;
    private String repeatedPassword;
    @NotNull
    @NotEmpty
    @ValidDateOfBirth
    private String dateOfBirth;
    @NotNull
    @NotEmpty
    private String bodyWeight;
    @NotNull
    @NotEmpty
    private String gender;
}
