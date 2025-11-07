package com.event.AuthenticationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.AuthenticationService.dto.AuthDto;
import com.event.AuthenticationService.dto.ReturnDto;
import com.event.AuthenticationService.dto.SignupDto;
import com.event.AuthenticationService.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ReturnDto> signUp(@RequestBody SignupDto cred) {
        ReturnDto response = new ReturnDto();
        boolean isSuccess = authService.signUp(cred, response);
        
        if (isSuccess) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthDto cred) {
        boolean isAuthenticated = authService.authenticate(cred);
        
        if (isAuthenticated) {
            return ResponseEntity.ok("Authentication successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }
}