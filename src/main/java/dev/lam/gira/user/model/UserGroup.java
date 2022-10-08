package dev.lam.gira.user.model;

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
@Table(name = UserEntity.UserGroup.TABLE_NAME)
public class UserGroup extends BaseEntity {
    @Column(name = UserEntity.UserGroup.NAME)
    @Size(min = 5, max = 100, message = "Name must have length between {min} - {max} characters")
    private String name;

    @Column(name = UserEntity.UserGroup.DESCRIPTION)
    @NotBlank(message = "Description must not be blank")
    private String description;

    @Column(name = UserEntity.UserGroup.CODE)
    @Size(min = 3, max = 10, message = "Code must have length between {min} - {max} characters")
    private String code;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = UserEntity.UserMappedUserGroup.JOIN_TABLE,
            joinColumns = @JoinColumn(name = UserEntity.UserMappedUserGroup.JOIN_TABLE_USER_GROUP_ID),
            inverseJoinColumns =  @JoinColumn(name = UserEntity.UserMappedUserGroup.JOIN_TABLE_USER_ID)
    )
    private Set<User> users = new LinkedHashSet<>();

    public UserGroup addUser(User user) {
        this.users.add(user);
        user.getUserGroups().add(this);
        return this;
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getUserGroups().remove(this);
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

        UserGroup userGroup = (UserGroup) obj;

        return this.id != null && Objects.equals(this.id, userGroup.getId());
    }
}
