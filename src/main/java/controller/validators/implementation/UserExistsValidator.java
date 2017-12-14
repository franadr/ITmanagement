package controller.validators.implementation;

/**
 * Validator implementation for custom validator @UserExists
 *
 * To make sure that User (recognize by his Username) is not already existing in the DB
 */

import Services.UserService;
import controller.validators.annotations.UserExists;
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
