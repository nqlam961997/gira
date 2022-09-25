package dev.lam.gira.role.validation.validator;

import dev.lam.gira.role.dto.RoleDTO;
import dev.lam.gira.role.model.Role;
import dev.lam.gira.role.repository.RoleRepository;
import dev.lam.gira.role.validation.annotation.UniqueRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueRoleValidator implements ConstraintValidator<UniqueRole, RoleDTO> {
    private String message;

    private RoleRepository roleRepository;

    public UniqueRoleValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initialize(dev.lam.gira.role.validation.annotation.UniqueRole constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(RoleDTO roleDTO, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Role> roleOpt = roleRepository.findByNameOrCode(roleDTO.getName(), roleDTO.getCode());

        if (roleOpt.isEmpty()) {
            return true;
        }

        constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}
