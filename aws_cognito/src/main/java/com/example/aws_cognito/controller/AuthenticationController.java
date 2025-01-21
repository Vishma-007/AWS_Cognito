package com.example.aws_cognito.controller;

import com.example.aws_cognito.CognitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private CognitoService cognitoService;

    @PostMapping("/signup")
    public String signUpUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        try {
            cognitoService.signUpUser(username, password, email);
            return "User registered successfully. Please check your email to confirm the account.";
        } catch (Exception e) {
            return "Error during sign-up: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            AuthenticationResultType authResult = cognitoService.authenticateUser(username, password);
            return "Authentication successful. Token: " + authResult.getIdToken();
        } catch (Exception e) {
            return "Authentication failed: " + e.getMessage();
        }
    }

    @PostMapping("/confirm")
    public String confirmUser(@RequestParam String username, @RequestParam String confirmationCode) {
        try {
            cognitoService.confirmUser(username, confirmationCode);
            return "User confirmed successfully.";
        } catch (Exception e) {
            return "Error during confirmation: " + e.getMessage();
        }
    }
}

