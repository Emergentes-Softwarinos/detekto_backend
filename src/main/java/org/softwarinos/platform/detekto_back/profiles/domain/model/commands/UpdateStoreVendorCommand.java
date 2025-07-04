package org.softwarinos.platform.detekto_back.profiles.domain.model.commands;

public record UpdateStoreVendorCommand(
        Long storeVendorId,
        String username,
        String description,
        String gender,
        String age
        ) {

}
