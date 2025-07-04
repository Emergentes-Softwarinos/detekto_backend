package org.softwarinos.platform.detekto_back.profiles.interfaces.rest.resources;

public record UpdateStoreVendorResource(
        String username,
        String description,
        String gender,
        String age
) {
}
