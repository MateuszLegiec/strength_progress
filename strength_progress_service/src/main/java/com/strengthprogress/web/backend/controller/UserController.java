package com.strengthprogress.web.backend.controller;

import com.strengthprogress.web.backend.dto.UpdateEmailDTO;
import com.strengthprogress.web.backend.dto.UpdatePasswordDTO;
import com.strengthprogress.web.backend.expection.ObjectAlreadyExistException;
import com.strengthprogress.web.backend.expection.ObjectNotFoundException;
import com.strengthprogress.web.backend.model.User;
import com.strengthprogress.web.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler({ObjectNotFoundException.class, ObjectAlreadyExistException.class})
    public String handleException(final Exception e){
        return e.getMessage();
    }


    @PostMapping("/registration")
    public String registration(@Valid @RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/registration-confirmation")
    public String registrationConfirmation(@RequestParam("token") String token){
        return userService.confirmRegistration(token);
    }

    @PostMapping("/new-email")
    public String updateEmail(@Valid @RequestBody UpdateEmailDTO dto){
        return userService.updateEmail(dto);
    }

    @PostMapping("/new-email-confirmation")
    public String updateEmailConfirmation(@RequestParam("token") String token){
        return userService.confirmUpdateEmail(token);
    }

    @PostMapping("/new-password")
    public String updatePassword(@Valid @RequestBody UpdatePasswordDTO dto){
        return userService.updatePassword(dto);
    }

    @PostMapping("/new-password-confirmation")
    public String updatePasswordConfirmation(@RequestParam("token") String token){
        return userService.confirmUpdatePassword(token);
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("email") @Email String email){
        return userService.resetPassword(email);
    }

    @PostMapping("/reset-password-confirmation")
    public String resetPasswordConfirmation(@RequestParam("token") String token){
        return userService.confirmResetPassword(token);
    }
}
