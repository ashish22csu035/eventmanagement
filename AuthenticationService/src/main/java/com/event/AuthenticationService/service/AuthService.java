package com.event.AuthenticationService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.event.AuthenticationService.dto.AuthDto;
import com.event.AuthenticationService.dto.ReturnDto;
import com.event.AuthenticationService.dto.SignupDto;
import com.event.AuthenticationService.repository.AuthRepository;

@Service
public class AuthService {
    
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, BCryptPasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean signUp(SignupDto cred, ReturnDto response) {
        StringBuffer error = new StringBuffer();
        
        // Encrypt password
        String encryptedPassword = passwordEncoder.encode(cred.getPassword());
        cred.setPassword(encryptedPassword);
        
        boolean isSuccess = authRepository.signUp(cred, error);
        
        if (isSuccess) {
            response.setStatus("Success");
            response.setEmail(cred.getEmail());
        } else {
            response.setStatus("Failed: " + error.toString());
            response.setEmail(cred.getEmail());
        }
        
        return isSuccess;
    }

    public boolean authenticate(AuthDto cred) {
        StringBuffer password = new StringBuffer();
        StringBuffer error = new StringBuffer();
        
        boolean isFound = authRepository.getPasswordFromEmail(cred.getEmail(), password, error);
        
        if (!isFound) {
            return false;
        }
        
        // Compare passwords
        return passwordEncoder.matches(cred.getPassword(), password.toString());
    }
}