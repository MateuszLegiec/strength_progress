package com.strengthprogress.web.backend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendAccountVerificationEmail(String email, String token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("");
        mailMessage.setText("To confirm your account, please click here : " +"http://localhost:8080/confirm-registration?token="+token);

        javaMailSender.send(mailMessage);
    }

    @Async
    public void sendPasswordVerificationEmail(String email, String token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Password changed!");
        mailMessage.setFrom("");
        mailMessage.setText("To confirm your action, please click here : " +"http://localhost:8080/confirm-password?token="+token);

        javaMailSender.send(mailMessage);
    }

    @Async
    public void sendEmailVerificationEmail(String email, String token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Email changed!");
        mailMessage.setFrom("");
        mailMessage.setText("To confirm your action, please click here : " +"http://localhost:8080/confirm-email?token="+token);

        javaMailSender.send(mailMessage);
    }

    @Async
    public void sendResetPasswordVerificationEmail(String email, String token) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Password changed!");
        mailMessage.setFrom("");
        mailMessage.setText("To confirm your action, please click here : " + "http://localhost:8080/confirm-reset?token=" + token + " .After that we will send you next email with generated password.");

        javaMailSender.send(mailMessage);
    }

    @Async
    public void sendPasswordEmail(String email, String newPassword) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Password changed!");
        mailMessage.setFrom("");
        mailMessage.setText("Your new generated password:" + newPassword);

        javaMailSender.send(mailMessage);
    }
}
