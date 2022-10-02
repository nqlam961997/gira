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
@Table(name = RoleEntity.Operation.TABLE_NAME)
public class Operation extends BaseEntity {
    @Column(name = RoleEntity.Operation.NAME)
    @Size(min = 5, max = 100, message = "Name must have length between {min} - {max} characters")
    private String name;

    @Column(name = RoleEntity.Operation.DESCRIPTION)
    @NotBlank(message = "Description must not be blank")
    private String description;

    @Column(name = RoleEntity.Operation.CODE)
    @Size(min = 3, max = 10, message = "Code must have length between {min} - {max} characters")
    private String code;

    @Column(name = RoleEntity.Operation.TYPE, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToMany(mappedBy = RoleEntity.RoleMappedService.SERVICE_MAPPED_ROLE)
    private Set<Role> roles = new LinkedHashSet<>();

    public enum Type {
        SAVE_OR_UPDATE,
        FETCH,
        REMOVE
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

        Operation service = (Operation) obj;

        return this.id != null && Objects.equals(this.id, service.getId());
    }
}
