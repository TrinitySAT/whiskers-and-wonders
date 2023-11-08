package com.whiskersandwonders.controllers;

import com.whiskersandwonders.entities.UserRepository;
import com.whiskersandwonders.services.AuthenticationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {
    private UserRepository usersDao;
    private AuthenticationService authService;

    UserApiController(UserRepository usersDao, AuthenticationService authService) {
        this.usersDao = usersDao;
        this.authService = authService;
    }

    @GetMapping(value = "/dashboard/send/validation/error", produces = "text/plain")
    public String sendValidationError(){
        return "validationError";
    }

}
