package dev.lam.gira.user.dto;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupWithUserDTO {
    private UUID id;

    private String name;

    private String description;

    private String code;

    private Set<UserDTO> userDTOS = new LinkedHashSet<>();
}
