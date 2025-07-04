package org.softwarinos.platform.detekto_back.profiles.interfaces.rest.transform;

import org.softwarinos.platform.detekto_back.profiles.domain.model.aggregates.StoreVendor;
import org.softwarinos.platform.detekto_back.profiles.interfaces.rest.resources.StoreVendorResource;

public class StoreVendorResourceFromEntityAssembler {
    public static StoreVendorResource toResourceFromEntity(StoreVendor storeVendor) {
        return new StoreVendorResource(
                storeVendor.getId(),
                storeVendor.getUsername(),
                storeVendor.getEmail(),
                storeVendor.getPhoneNumber(),
                storeVendor.getPassword(),
                storeVendor.getStoreVendorImage() != null ? storeVendor.getStoreVendorImage().getImageUrl() : null // Aquí evitamos el NullPointerException

        );
    }
}
