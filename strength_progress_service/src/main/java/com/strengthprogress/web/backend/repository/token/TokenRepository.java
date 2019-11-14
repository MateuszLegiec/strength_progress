package com.strengthprogress.web.backend.repository.token;


import com.strengthprogress.web.backend.model.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByToken(String token);
    void deleteByUserUsername(@Email String user_username);
    void deleteAllByExpiryDateAfter(LocalDateTime expiryDate);
}
