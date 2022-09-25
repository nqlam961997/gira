package dev.lam.gira.role.dto;

import dev.lam.gira.role.validation.annotation.UniqueRole;
import dev.lam.gira.role.validation.annotation.UniqueRoleName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@UniqueRole(message = "{role.existed}")
public class RoleDTO {
    private UUID id;

    @Size(min = 5, max = 100, message = "Name must have length between {min} - {max} characters")
    private String name;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @Size(min = 3, max = 10, message = "Code must have length between {min} - {max} characters")
    private String code;
}
