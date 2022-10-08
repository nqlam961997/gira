package dev.lam.gira.security.validation.validator;

import dev.lam.gira.security.validation.annotation.MustBeExistedUser;
import dev.lam.gira.user.model.User;
import dev.lam.gira.user.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class MustBeExistedUserValidator implements ConstraintValidator<MustBeExistedUser, String> {
    private String message;

    private final UserRepository userRepository;

    public MustBeExistedUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(MustBeExistedUser constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> userOpt = userRepository.findByUsername(s);

        if (userOpt.isPresent()) {
            return true;
        }

        constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
