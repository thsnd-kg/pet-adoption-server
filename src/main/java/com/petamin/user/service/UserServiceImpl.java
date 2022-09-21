package com.petamin.user.service;

import com.petamin.user.dto.CreateUserDto;
import com.petamin.user.entity.User;
import com.petamin.user.entity.UserProfile;
import com.petamin.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void createUser(CreateUserDto dto) {
        User newUser = new User();

        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(encoder.encode(dto.getPassword()));

        UserProfile profile = new UserProfile();
        profile.setUsername(dto.getUsername());
        profile.setDisplayName(dto.getUsername());

        newUser.setProfile(profile);
        repository.save(newUser);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserByUsername(String username){
        return repository.findByUsername(username).orElseThrow(() -> new IllegalStateException("Username:" + username+" does not exist"));
    }

    @Override
    public User getMyProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails)
            username = ((UserDetails)principal).getUsername();
        else
            username = principal.toString();

        return getUserByUsername(username);
    }

}
