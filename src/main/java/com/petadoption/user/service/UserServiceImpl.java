package com.petadoption.user.service;

import com.petadoption.user.dto.CreateUserDto;
import com.petadoption.user.entity.User;
import com.petadoption.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private PasswordEncoder encoder;

    private  AuthenticationManager authenticationManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.repository = userRepository;
        this.encoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public boolean isTakenUsername(String username) {
        return repository.countByUsername(username.toLowerCase()) >= 1;
    }

    @Override
    public boolean isTakenEmail(String email) {
        return repository.countByEmail(email) >= 1;
    }

    @Override
    public User createUser(CreateUserDto dto) {
        User newUser = new User();

        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(encoder.encode(dto.getPassword()));

        return repository.save(newUser);
    }







}
