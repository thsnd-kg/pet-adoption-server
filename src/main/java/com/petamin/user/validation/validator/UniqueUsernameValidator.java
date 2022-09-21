package com.petamin.user.validation.validator;

import com.petamin.common.util.ValidatorUtils;
import com.petamin.user.service.UserService;
import com.petamin.user.validation.annotation.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private String message;
    private UserService service;

    public UniqueUsernameValidator(UserService userService) {
        service = userService;
    }

    @Override
    public void initialize(UniqueUsername uniqueUsername) {
        message = uniqueUsername.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(username == null)
            return false;

        boolean isTaken = service.isTakenUsername(username);

        if(!isTaken)
            return true;

        ValidatorUtils.addError(context, message);
        return false;
    }

}
