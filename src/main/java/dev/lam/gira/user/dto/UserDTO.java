package dev.lam.gira.user.dto;

import dev.lam.gira.user.model.User;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private UUID id;

    private String email;

    private String password;

    private String username;

    private String displayName;

    private String firstName;

    private String lastName;

    private String avatar;

    private String department;

    private User.UserStatus status;
}
