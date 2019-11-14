package com.strengthprogress.web.backend.dto;

import com.strengthprogress.web.backend.validation.EmailMatches;
import com.strengthprogress.web.backend.validation.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@EmailMatches
public class UpdateEmailDTO{
    @Email
    private String email;
    private String repeatedEmail;
    @ValidPassword
    private String password;

}
