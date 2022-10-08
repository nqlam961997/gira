package dev.lam.gira.user.model;

import dev.lam.gira.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = UserEntity.User.TABLE_NAME)
public class User extends BaseEntity {
    @Column(name = UserEntity.User.EMAIL,
            length = 100,
            nullable = false,
            unique = true,
            updatable = false)
    private String email;

    @NotBlank
    @Column(name = UserEntity.User.PASSWORD)
    private String password;

    @Column(name = UserEntity.User.USERNAME, unique = true, nullable = false, length = 100)
    private String username;

    @Column(name = UserEntity.User.DISPLAY_NAME, nullable = false)
    private String displayName;

    @Column(name = UserEntity.User.FIRST_NAME)
    private String firstName;

    @Column(name = UserEntity.User.LAST_NAME)
    private String lastName;

    @Column(name = UserEntity.User.AVATAR)
    private String avatar;

    @Column(name = UserEntity.User.DEPARTMENT)
    private String department;

    @Column(name = UserEntity.User.STATUS, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToMany(mappedBy = UserEntity.UserMappedUserGroup.SERVICE_MAPPED_USER)
    private Set<UserGroup> userGroups = new LinkedHashSet<>();

    public enum UserStatus {
        ACTIVE,
        TEMPORARY_BLOCKED,
        PERNAMENT_BLOCKED
    }

}
