package com.strengthprogress.web.backend.model.token;

import com.strengthprogress.web.backend.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class NewPasswordToken extends Token {

    private String newPassword;

    public NewPasswordToken(User user, String newPassword) {
        super(user);
        this.newPassword = newPassword;
    }
}
