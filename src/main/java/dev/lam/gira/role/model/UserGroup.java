package dev.lam.gira.role.model;

import dev.lam.gira.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = RoleEntity.UserGroup.TABLE_NAME)
public class UserGroup extends BaseEntity {
    @Column(name = RoleEntity.UserGroup.NAME)
    @Size(min = 5, max = 100, message = "Name must have length between {min} - {max} characters")
    private String name;

    @Column(name = RoleEntity.UserGroup.DESCRIPTION)
    @NotBlank(message = "Description must not be blank")
    private String description;

    @Column(name = RoleEntity.UserGroup.CODE)
    @Size(min = 3, max = 10, message = "Code must have length between {min} - {max} characters")
    private String code;
}
