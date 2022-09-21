package com.petamin.user.service;


import com.petamin.user.dto.CreateUserDto;
import com.petamin.user.entity.User;

import java.util.List;

public interface UserService {
    boolean isTakenUsername(String username);
    boolean isTakenEmail(String email);
     void createUser(CreateUserDto dto);
    List<User> getAllUsers();

    User getMyProfile();
}
