package com.crema.creamaspring.auth.model;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;

}
