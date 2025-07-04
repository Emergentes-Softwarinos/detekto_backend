package org.softwarinos.platform.detekto_back.iam.domain.model.entities;


import jakarta.persistence.*;
import lombok.*;
import org.softwarinos.platform.detekto_back.iam.domain.model.valueobjects.Roles;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Roles name;


    public Role(Roles name) {
        this.name = name;
    }

    public String getStringName() {
        return name.name();
    }

    public static Role getDefaultRole() {
        return new Role(Roles.ROLE_USER);
    }

    // este metodo es para convertir un string a un objeto Role
    public static Role toRoleFromName(String name) {
        return new Role(Roles.valueOf(name));
    }

    public static List<Role> validateRoleSet(List<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return List.of(getDefaultRole());
        }

        return roles;
    }
    public static Role getFirstRole(List<Role> roles) {
        return roles.get(0);
    }



}
