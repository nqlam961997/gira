package dev.lam.gira.role.validation.validator;

import dev.lam.gira.role.model.Role;
import dev.lam.gira.role.repository.RoleRepository;
import dev.lam.gira.role.validation.annotation.UniqueRoleName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName, String> {
    private String message;

    private RoleRepository roleRepository;

    public UniqueRoleNameValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initialize(UniqueRoleName constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String roleName, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Role> roleOpt = roleRepository.findByName(roleName);

        if (roleOpt.isEmpty()) {
            return true;
        }

        constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
