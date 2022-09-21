package com.petadoption.user.controller;


import com.petadoption.common.ResponseHandler;
import com.petadoption.user.dto.CreateUserDto;
import com.petadoption.user.dto.UpdateUserDto;
import com.petadoption.user.entity.User;
import com.petadoption.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController {
    // Do something

}
