package com.example.demoproject.services.impl;

import com.example.demoproject.entities.Roles;
import com.example.demoproject.entities.Users;
import com.example.demoproject.repositories.RoleRepository;
import com.example.demoproject.repositories.UserRepository;
import com.example.demoproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users myUser = userRepository.findByEmail(s);
        if(myUser!=null){

            User secUser = new User(myUser.getEmail(), myUser.getPassword(), myUser.getRoles());
            return secUser;

        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Users createUser(Users user) {
        Users checkUser = userRepository.findByEmail(user.getEmail());
        if(checkUser==null){
            Roles role = roleRepository.findByRoles("ROLE_USER");
            if(role!=null){
                ArrayList<Roles> roles = new ArrayList<>();
                roles.add(role);
                user.setRoles(roles);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);
            }

        }
        return null;
    }
}
