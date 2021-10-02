package com.example.security.service;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String email, String password);
}
