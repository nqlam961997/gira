package dev.lam.gira.role.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private UUID id;
    private String name;
    private String description;
    private String code;
}
