package org.softwarinos.platform.detekto_back.iam.interfaces.rest.transform;


import org.softwarinos.platform.detekto_back.iam.domain.model.aggregates.User;
import org.softwarinos.platform.detekto_back.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(
            user.getId(),
            user.getEmail(),
            token
        );
    }

}
