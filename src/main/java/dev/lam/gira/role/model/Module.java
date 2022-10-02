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
@Table(name = RoleEntity.Module.TABLE_NAME)
public class Module extends BaseEntity {
    @Column(name = RoleEntity.Module.NAME)
    @Size(min = 5, max = 100, message = "Name must have length between {min} - {max} characters")
    private String name;

    @Column(name = RoleEntity.Module.DESCRIPTION)
    @NotBlank(message = "Description must not be blank")
    private String description;

    @Column(name = RoleEntity.Module.CODE)
    @Size(min = 3, max = 10, message = "Code must have length between {min} - {max} characters")
    private String code;

    @Override
    public boolean equals(Object obj) {
        Module module = (Module) obj;
        return super.equals(obj)
                && module.name.equals(name)
                && module.code.equals(code);
    }
}
