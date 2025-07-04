package org.softwarinos.platform.detekto_back.iam.interfaces.rest.transform;


import org.softwarinos.platform.detekto_back.iam.domain.model.aggregates.User;
import org.softwarinos.platform.detekto_back.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toUserResourceFromEntity(User entity) {
        var roles = entity.getRoles().stream().map(role -> role.getStringName()).toList();

        return new UserResource(
            entity.getId(),
            entity.getEmail(),
            roles
        );
    }

}
