package com.example.mac.security.service;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String email, String password);
}
