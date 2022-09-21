package com.petamin.user.controller;


import com.petamin.common.ResponseHandler;
import com.petamin.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
    // Do something
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public Object getAllUsers(){
        try{
            return ResponseHandler.getResponse(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/me")
    public Object getMyProfile(){
        try{
            return ResponseHandler.getResponse(userService.getMyProfile(), HttpStatus.OK);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
