package org.softwarinos.platform.detekto_back.profiles.domain.services;

import org.softwarinos.platform.detekto_back.profiles.domain.model.aggregates.StoreVendor;
import org.softwarinos.platform.detekto_back.profiles.domain.model.commands.CreateStoreVendorCommand;
import org.softwarinos.platform.detekto_back.profiles.domain.model.commands.UpdateStoreVendorCommand;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface StoreVendorCommandService {

    Long createStoreVendor(CreateStoreVendorCommand command);
    Optional<StoreVendor> updateStoreVendor(UpdateStoreVendorCommand command);
    void deleteStoreVendor(Long storeVendorId);

    Optional<StoreVendor> UpdateStoreVendorImage(MultipartFile file, StoreVendor storeVendor) throws IOException;
    Optional<StoreVendor> deleteStoreVendorImage(Long storeVendorId) throws IOException;
}
