package com.petadoption.user.service;


import com.petadoption.user.dto.CreateUserDto;
import com.petadoption.user.dto.UpdateUserDto;
import com.petadoption.user.entity.User;

import java.util.List;

public interface UserService {
    boolean isTakenUsername(String username);
    boolean isTakenEmail(String email);
    User createUser(CreateUserDto dto);

}
