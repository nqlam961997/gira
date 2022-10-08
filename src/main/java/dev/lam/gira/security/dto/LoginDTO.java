package dev.lam.gira.security.dto;

import dev.lam.gira.security.validation.annotation.MustBeExistedUser;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @MustBeExistedUser
    public String username;
    public String password;
}
