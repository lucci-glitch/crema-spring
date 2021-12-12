package com.crema.creamaspring.auth.controller;


import com.crema.creamaspring.auth.model.User;
import com.crema.creamaspring.auth.service.SecurityService;
import com.crema.creamaspring.auth.service.UserService;
import com.crema.creamaspring.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//TODO: fixa iordning så att den kan registrera och logga in users
//TODO: Mitt stora feta problem är att koppla react med detta ????

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    //TODO: mer likt detta?
//    @GetMapping
//    public ResponseEntity<Void> get(HttpServletRequest request) {
//        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
//        LOGGER.info("{}={}", token.getHeaderName(), token.getToken());
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/registration")
      public void registration() {
        System.out.println("REGISTER PLZ.");
    }

//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//
//        return "registration";
//    }

    @PostMapping("/registration")

    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
      public void login() {
        System.out.println("Nu är vi vid loginen.");
    //TODO: kommer aldrig in här??
    }


//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "login";
//    }

}
