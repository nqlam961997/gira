package dev.lam.gira.role.model;

import dev.lam.gira.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = RoleEntity.RoleMappedService.JOIN_TABLE,
            joinColumns = @JoinColumn(name = RoleEntity.RoleMappedService.JOIN_TABLE_ROLE_ID),
            inverseJoinColumns =  @JoinColumn(name = RoleEntity.RoleMappedService.JOIN_TABLE_SERVICE_ID)
    )
    private Set<Operation> operations = new LinkedHashSet<>();

    public void removeOperation(Operation operation) {
        this.operations.remove(operation);
        operation.getRoles().remove(this);
    }

    public Role addOperation(Operation operation) {
        this.operations.add(operation);
        operation.getRoles().add(this);
        return this;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) {
            return false;
        }

        Role role = (Role) obj;

        return this.id != null && Objects.equals(this.id, role.getId());
    }
}
