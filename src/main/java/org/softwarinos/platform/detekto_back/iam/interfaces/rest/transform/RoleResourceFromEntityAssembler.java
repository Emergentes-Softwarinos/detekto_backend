package org.softwarinos.platform.detekto_back.iam.interfaces.rest.transform;


import org.softwarinos.platform.detekto_back.iam.domain.model.entities.Role;
import org.softwarinos.platform.detekto_back.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toRoleResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }

}
