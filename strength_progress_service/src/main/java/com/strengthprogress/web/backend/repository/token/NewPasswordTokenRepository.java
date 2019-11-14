package com.strengthprogress.web.backend.repository.token;

import com.strengthprogress.web.backend.model.token.NewPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface NewPasswordTokenRepository extends JpaRepository<NewPasswordToken,Long> {
    Optional<NewPasswordToken> findByToken(String token);
    void deleteByUserUsername(@Email String user_username);

}
