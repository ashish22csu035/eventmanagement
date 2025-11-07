package com.event.AuthenticationService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnDto {
    
    @JsonProperty("Status")
    private String status;
    
    @JsonProperty("Email")
    private String email;

    public ReturnDto() {
    }

    public ReturnDto(String status, String email) {
        this.status = status;
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}