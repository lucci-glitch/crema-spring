package com.crema.creamaspring.auth.controller;


import com.crema.creamaspring.auth.model.User;
import com.crema.creamaspring.auth.service.SecurityService;
import com.crema.creamaspring.auth.service.UserService;
import com.crema.creamaspring.models.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

//    @Autowired
//    private UserValidator userValidator;

    // TODO: JWT här självklart
    // testade @Valid men har inte haft så mycket lycka med det
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestParam String user, @Valid @RequestParam String password, @Valid @RequestParam String passwordConfirm) {
//        if (password.equals("hejhejhej")) {
//            return new ResponseEntity<>("Detta gick bra du är inloggad", HttpStatus.ACCEPTED);
//        } else {
//            return new ResponseEntity<>("YOU SHALL NOT PASS", HttpStatus.UNAUTHORIZED);
//        }
        return new ResponseEntity<>("Detta gick bra du är inloggad", HttpStatus.ACCEPTED);
    }


    @PostMapping("/registration")
    public ResponseEntity<String> registration( @Valid @RequestParam String username, @Valid @RequestParam String password, @Valid @RequestParam String passwordConfirm) {
        User registerUser = new User(username, password, passwordConfirm);

        //TODO: validation, annoteringar?

        userService.save(registerUser);
        return new ResponseEntity<>("Detta gick bra du är inloggad", HttpStatus.ACCEPTED);
    }







}
