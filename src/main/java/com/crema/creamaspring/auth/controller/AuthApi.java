package com.crema.creamaspring.auth.controller;

import com.crema.creamaspring.JwtTokenUtil;
import com.crema.creamaspring.auth.model.User;
import com.crema.creamaspring.auth.model.AuthRequest;
import com.crema.creamaspring.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/auth")
public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public AuthApi(AuthenticationManager authenticationManager,
                   JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;

        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(), request.getPassword()
                            )
                    );

            UserDetails user = (UserDetails) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateAccessToken(new User(user.getUsername(), user.getPassword()))
                    )
                    .body(user.getUsername());
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registration(@Valid @RequestParam String username, @Valid @RequestParam String password, @Valid @RequestParam String passwordConfirm) {
        User registerUser = new User(username, password, passwordConfirm);

        //TODO: validation, annoteringar?

        userService.save(registerUser);
        return new ResponseEntity<>("Detta gick bra du är inloggad", HttpStatus.ACCEPTED);
    }


}
