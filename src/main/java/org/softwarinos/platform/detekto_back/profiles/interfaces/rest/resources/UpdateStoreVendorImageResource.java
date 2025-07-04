package org.softwarinos.platform.detekto_back.profiles.interfaces.rest.resources;

import org.springframework.web.multipart.MultipartFile;

public record UpdateStoreVendorImageResource(
        MultipartFile storeVendorImage
) {
}
