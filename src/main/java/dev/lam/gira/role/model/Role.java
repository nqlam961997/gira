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
@Table(name = RoleEntity.Role.TABLE_NAME)
public class Role extends BaseEntity {
    @Column(name = RoleEntity.Role.NAME)
    @Size(min = 5, max = 100, message = "Name must have length between {min} - {max} characters")
    private String name;

    @Column(name = RoleEntity.Role.DESCRIPTION)
    @NotBlank(message = "Description must not be blank")
    private String description;

    @Column(name = RoleEntity.Role.CODE)
    @Size(min = 3, max = 10, message = "Code must have length between {min} - {max} characters")
    private String code;

    @Override
    public boolean equals(Object obj) {
        Role role = (Role) obj;
        return super.equals(obj)
                && role.name.equals(name)
                && role.code.equals(code);
    }
}
