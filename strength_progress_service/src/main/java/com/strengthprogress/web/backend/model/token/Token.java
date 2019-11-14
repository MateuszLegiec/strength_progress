package com.strengthprogress.web.backend.model.token;

import com.strengthprogress.web.backend.model.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Token {

    private static final int EXPIRATION = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDateTime expiryDate;

    public Token(User user){
        this.user = user;
        expiryDate = LocalDateTime.now().plusHours(EXPIRATION);
        token = UUID.randomUUID().toString();
    }

}