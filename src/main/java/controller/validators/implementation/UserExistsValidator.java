package controller.validators.implementation;

import Services.UserService;
import controller.validators.annotations.UserExists;
import models.entites.jpa.User;

import javax.ejb.EJB;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserExistsValidator implements ConstraintValidator<UserExists, String> {

    @EJB(name="UserServiceImpl") private UserService userService;
    @Override
    public void initialize(UserExists userExists) {

    }

    @Override
    public boolean isValid(String u, ConstraintValidatorContext constraintValidatorContext) {

        return userService.getSpecificUser(u) == null;
    }
}
