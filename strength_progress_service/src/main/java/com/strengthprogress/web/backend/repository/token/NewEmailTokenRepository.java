package com.strengthprogress.web.backend.repository.token;

import com.strengthprogress.web.backend.model.token.NewEmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.Optional;

@Repository
public interface NewEmailTokenRepository extends JpaRepository<NewEmailToken,Long> {
    Optional<NewEmailToken> findByToken(String token);
    void deleteByUserUsername(@Email String user_username);
}
