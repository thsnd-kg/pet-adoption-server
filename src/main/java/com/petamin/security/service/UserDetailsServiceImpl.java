package com.petamin.security.service;

import com.petamin.security.dto.UserDetailsDto;
import com.petamin.user.entity.User;
import com.petamin.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository repository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);

        if(!user.isPresent())
            throw new UsernameNotFoundException("Username is not existed.");

        return new UserDetailsDto(username, user.get().getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
//        return new UserDetailsDto(username, user.get().getPassword());
    }

}