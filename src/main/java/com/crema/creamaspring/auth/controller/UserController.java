package com.crema.creamaspring.auth.controller;


import com.crema.creamaspring.auth.model.User;
import com.crema.creamaspring.auth.service.SecurityService;
import com.crema.creamaspring.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;




@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }


    @PostMapping("/registration")
    public ResponseEntity<String> registration( @Valid @RequestParam String username, @Valid @RequestParam String password, @Valid @RequestParam String passwordConfirm) {
        User registerUser = new User(username, password, passwordConfirm);

        //TODO: validation, annoteringar?

        userService.save(registerUser);
        return new ResponseEntity<>("Detta gick bra du är inloggad", HttpStatus.ACCEPTED);
    }







}
