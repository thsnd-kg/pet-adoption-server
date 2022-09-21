package com.petamin.security.controller;

import com.petamin.common.ResponseHandler;
import com.petamin.security.dto.LoginDto;
import com.petamin.security.jwt.JwtUtils;
import com.petamin.user.dto.CreateUserDto;
import com.petamin.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder encoder;



    public AuthController(
            AuthenticationManager authManager,
            JwtUtils jwtUtils,
            UserService userService,
            PasswordEncoder encoder
    ) {
        authenticationManager = authManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.encoder = encoder;
    }


    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Authentication auth = null;

        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtils.generateJwtToken(auth);
            // log history - AOP
            return ResponseHandler.getResponse(token, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("{} has been logged in with wrong password: {}" + dto.getUsername() + e.getMessage() );
        }

        return ResponseHandler.getResponse("Username or password is invalid.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/register")
    public Object register(@RequestBody CreateUserDto dto, BindingResult errors) {
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            userService.createUser(dto);
            return ResponseHandler.getResponse(HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
