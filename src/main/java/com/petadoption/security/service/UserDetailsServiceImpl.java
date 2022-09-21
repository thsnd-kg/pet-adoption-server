package com.petadoption.security.service;

import com.petadoption.security.dto.UserDetailsDto;
import com.petadoption.user.entity.User;
import com.petadoption.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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