package com.example.demoproject.services;

import com.example.demoproject.entities.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Users getUserByEmail(String email);
    Users createUser(Users user);
}
