package com.strengthprogress.web.backend.service;

import com.strengthprogress.web.backend.dto.UpdateEmailDTO;
import com.strengthprogress.web.backend.dto.UpdatePasswordDTO;
import com.strengthprogress.web.backend.expection.ObjectAlreadyExistException;
import com.strengthprogress.web.backend.expection.ObjectNotFoundException;
import com.strengthprogress.web.backend.model.Role;
import com.strengthprogress.web.backend.model.User;
import com.strengthprogress.web.backend.model.token.NewEmailToken;
import com.strengthprogress.web.backend.model.token.NewPasswordToken;
import com.strengthprogress.web.backend.model.token.Token;
import com.strengthprogress.web.backend.repository.UserRepository;
import com.strengthprogress.web.backend.repository.token.NewEmailTokenRepository;
import com.strengthprogress.web.backend.repository.token.NewPasswordTokenRepository;
import com.strengthprogress.web.backend.repository.token.TokenRepository;
import com.strengthprogress.web.backend.util.PasswordGenerator;
import com.strengthprogress.web.backend.validation.ValidPassword;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final NewEmailTokenRepository newEmailTokenRepository;
    private final NewPasswordTokenRepository newPasswordTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository, NewEmailTokenRepository newEmailTokenRepository, NewPasswordTokenRepository newPasswordTokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.newEmailTokenRepository = newEmailTokenRepository;
        this.newPasswordTokenRepository = newPasswordTokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailService = mailService;
    }

    public String register(User user) throws ObjectAlreadyExistException {
        if (userRepository.existsUserByUsername(user.getUsername()))
            throw new ObjectAlreadyExistException(User.class,"email",user.getUsername());

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAuthorities(Collections.singleton(Role.ROLE_USER));
        user.setEnabled(false);
        user = userRepository.save(user);

        Token token = new Token(user);
        tokenRepository.save(token);

        mailService.sendAccountVerificationEmail(user.getUsername(),token.getToken());
        return "Message was send to your email";
    }

    public String confirmRegistration(String token) throws ObjectNotFoundException {
        Optional<Token> savedToken = tokenRepository.findByToken(token);
        if (savedToken.isPresent()){
            User user = savedToken.get().getUser();
            if (LocalDateTime.now().isAfter(savedToken.get().getExpiryDate())){
                userRepository.delete(user);
                return "Registration token is expired, please login again";
            }
            user.setEnabled(true);
            userRepository.save(user);
            return "Registration confirmed";
        } else {
            throw new ObjectNotFoundException(Token.class,"token",token);
        }
    }

    public String updateEmail(UpdateEmailDTO dto){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        newEmailTokenRepository.deleteByUserUsername(user.getUsername());

        NewEmailToken newEmailToken = new NewEmailToken(user,dto.getEmail());
        newEmailTokenRepository.save(newEmailToken);

        mailService.sendEmailVerificationEmail(dto.getEmail(),newEmailToken.getToken());
        return "Message was send to your email";
    }

    public String confirmUpdateEmail(String token){
        Optional<NewEmailToken> savedToken = newEmailTokenRepository.findByToken(token);
        if (savedToken.isPresent()){
            if (LocalDateTime.now().isAfter(savedToken.get().getExpiryDate())) {
                tokenRepository.delete(savedToken.get());
                return "Token is expired";
            }
            User user = savedToken.get().getUser();
            user.setUsername(savedToken.get().getNewEmail());
            userRepository.save(user);
            return "Email confirmed";
        } else {
            throw new ObjectNotFoundException(Token.class,"token",token);
        }
    }

    public String updatePassword(UpdatePasswordDTO dto){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        newPasswordTokenRepository.deleteByUserUsername(user.getUsername());

        NewPasswordToken newPasswordToken = new NewPasswordToken(user,bCryptPasswordEncoder.encode(dto.getOldPassword()));
        newPasswordTokenRepository.save(newPasswordToken);

        mailService.sendPasswordVerificationEmail(user.getUsername(),newPasswordToken.getToken());
        return "Message was send to your email";
    }

    public String confirmUpdatePassword(String token){
        Optional<NewPasswordToken> savedToken = newPasswordTokenRepository.findByToken(token);
        if (savedToken.isPresent()){
            if (LocalDateTime.now().isAfter(savedToken.get().getExpiryDate())) {
                tokenRepository.delete(savedToken.get());
                return "Token is expired";
            }
            User user = savedToken.get().getUser();
            user.setPassword(savedToken.get().getNewPassword());
            userRepository.save(user);
            return "Password confirmed";
        } else {
            throw new ObjectNotFoundException(Token.class,"token",token);
        }
    }

    public String resetPassword(@Email String email){
        Optional<User> optionalUser = userRepository.findByUsername(email);
        if (!optionalUser.isPresent())
            return "Email: " + email + " not found";
        tokenRepository.deleteByUserUsername(email);

        Token token = new Token(optionalUser.get());
        tokenRepository.save(token);

        mailService.sendResetPasswordVerificationEmail(email,token.getToken());
        return "Message was send to your email";
    }

    public String confirmResetPassword(String token){
        Optional<Token> savedToken = tokenRepository.findByToken(token);
        if (savedToken.isPresent()){
            if (LocalDateTime.now().isAfter(savedToken.get().getExpiryDate())) {
                tokenRepository.delete(savedToken.get());
                return "Token is expired";
            }
            User user = savedToken.get().getUser();
            String newPassword = PasswordGenerator.generate();
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(user);
            mailService.sendPasswordEmail(user.getUsername(),newPassword);
            return "Password was send to your email";
        } else {
            throw new ObjectNotFoundException(Token.class,"token",token);
        }
    }
}