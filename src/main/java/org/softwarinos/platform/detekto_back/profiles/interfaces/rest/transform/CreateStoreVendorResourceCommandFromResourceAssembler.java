package org.softwarinos.platform.detekto_back.profiles.interfaces.rest.transform;

import org.softwarinos.platform.detekto_back.profiles.domain.model.commands.CreateStoreVendorCommand;
import org.softwarinos.platform.detekto_back.profiles.interfaces.rest.resources.CreateStoreVendorResource;

public class CreateStoreVendorResourceCommandFromResourceAssembler {
    public static CreateStoreVendorCommand toCommandFromResource(CreateStoreVendorResource resource) {
        return new CreateStoreVendorCommand(
                resource.username(),
                resource.email(),
                resource.description(),
                resource.gender(),
                resource.age(),
                resource.password()
        );
    }
}
