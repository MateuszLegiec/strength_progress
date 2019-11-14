package com.strengthprogress.web.backend.dto;

import com.strengthprogress.web.backend.util.PasswordMatchable;
import com.strengthprogress.web.backend.validation.PasswordMatches;
import com.strengthprogress.web.backend.validation.ValidPassword;
import lombok.Data;

@Data
@PasswordMatches
public class DeleteUserDTO implements PasswordMatchable {
    private Long userId;
    private String password;
    private String repeatedPassword;
}
