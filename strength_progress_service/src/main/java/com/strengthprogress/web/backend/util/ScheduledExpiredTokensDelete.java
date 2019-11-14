package com.strengthprogress.web.backend.util;

import com.strengthprogress.web.backend.repository.token.TokenRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableAsync
public class ScheduledExpiredTokensDelete {

    private final TokenRepository tokenRepository;

    public ScheduledExpiredTokensDelete(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Async
    @Scheduled(fixedRate = 1000L * 60L * 60L * 24L)
    public void scheduledExpireTokensDelete(){
        tokenRepository.deleteAllByExpiryDateAfter(LocalDateTime.now());
    }
}
