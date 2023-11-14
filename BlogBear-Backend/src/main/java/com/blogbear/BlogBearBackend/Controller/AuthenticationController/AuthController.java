package com.blogbear.BlogBearBackend.Controller.AuthenticationController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")
    public String login() {
        return "login works";
    }

    @PostMapping("/register")
    public String register() {
        return "register works";
    }
}
