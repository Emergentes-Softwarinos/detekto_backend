package org.softwarinos.platform.detekto_back.iam.interfaces.rest.transform;


import org.softwarinos.platform.detekto_back.iam.domain.model.commands.SignInCommand;
import org.softwarinos.platform.detekto_back.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }

}
