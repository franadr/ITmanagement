package controller.validators.annotations;

import controller.validators.implementation.UserExistsValidator;
import models.entites.jpa.User;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = UserExistsValidator.class)
@Documented
public @interface UserExists {
    String message() default "{invalid.unique.username}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
