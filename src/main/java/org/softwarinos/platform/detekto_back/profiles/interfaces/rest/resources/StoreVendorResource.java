package org.softwarinos.platform.detekto_back.profiles.interfaces.rest.resources;

public record StoreVendorResource(
        Long id,
        String username,
        String email,
        String phoneNumber,
        String password,
        String imageUrl
) {
}
