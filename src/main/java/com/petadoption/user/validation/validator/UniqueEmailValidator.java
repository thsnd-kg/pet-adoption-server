package com.petadoption.user.validation.validator;

import com.petadoption.common.util.ValidatorUtils;
import com.petadoption.user.service.UserService;
import com.petadoption.user.validation.annotation.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private String message;
    private UserService service;

    public UniqueEmailValidator(UserService userService) {
        service = userService;
    }

    @Override
    public void initialize(UniqueEmail uniqueEmail) {
        message = uniqueEmail.message();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email == null)
            return false;

        boolean isTaken = service.isTakenEmail(email);

        if(!isTaken)
            return true;

        ValidatorUtils.addError(context, message);
        return false;
    }

}
