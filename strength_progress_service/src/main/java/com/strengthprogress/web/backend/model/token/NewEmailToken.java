package com.strengthprogress.web.backend.model.token;

import com.strengthprogress.web.backend.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class NewEmailToken extends Token {
    private String newEmail;
    public NewEmailToken(User user, String newEmail) {
        super(user);
        this.newEmail = newEmail;
    }
}
