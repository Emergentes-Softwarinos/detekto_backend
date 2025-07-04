package org.softwarinos.platform.detekto_back.profiles.interfaces.rest.transform;

import org.softwarinos.platform.detekto_back.profiles.domain.model.commands.UpdateStoreVendorCommand;
import org.softwarinos.platform.detekto_back.profiles.interfaces.rest.resources.UpdateStoreVendorResource;

// se actualiza solo el username y el age (de acuerdo a los atributos del command)
public class UpdateStoreVendorResourceCommandFromResourceAssembler {
    public static UpdateStoreVendorCommand toCommandFromResource(UpdateStoreVendorResource resource, Long storeVendorId) {
        return new UpdateStoreVendorCommand(
                storeVendorId,
                resource.username(),
                resource.description(),
                resource.gender(),
                resource.age()
        );
    }
}
