package org.softwarinos.platform.detekto_back.profiles.interfaces.rest.resources;

public record StoreVendorResource(
        Long id,
        String username,
        String email,
        String description,
        String gender,
        String age,
        String password,
        String imageUrl
) {
}
