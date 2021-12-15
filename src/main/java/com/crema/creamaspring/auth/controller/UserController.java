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
    public ResponseEntity<String> login(@Valid @RequestParam String username, @Valid @RequestParam String password) {
//        if (password.equals("hejhejhej")) {
//            return new ResponseEntity<>("Detta gick bra du är inloggad", HttpStatus.ACCEPTED);
//        } else {
//            return new ResponseEntity<>("YOU SHALL NOT PASS", HttpStatus.UNAUTHORIZED);
//        }

        return new ResponseEntity<>("Detta gick bra du är inloggad", HttpStatus.ACCEPTED);
    }


//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

    @PostMapping("/registration")
    public ResponseEntity<String> registration( @Valid @RequestParam String username, @Valid @RequestParam String password, @Valid @RequestParam String passwordConfirm) {
        User registerUser = new User(username, password, passwordConfirm);

        //TODO: validation, annoteringar?

        userService.save(registerUser);
        return new ResponseEntity<>("Detta gick bra du är inloggad", HttpStatus.ACCEPTED);
    }

    //TODO: csrf-token ska vi klart ha
//    @GetMapping
//    public ResponseEntity<Void> get(HttpServletRequest request) {
//        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
//        LOGGER.info("{}={}", token.getHeaderName(), token.getToken());
//        return ResponseEntity.ok().build();
//    }


    //TODO: gammalt skit relaterat till .jsp , only for ref
//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
//        userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        userService.save(userForm);
//
//        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//        return "redirect:/welcome";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "login";
//    }
//
//    @GetMapping({"/", "/welcome"})
//    public String welcome(Model model) {
//        return "welcome";
//    }




}
