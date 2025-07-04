package org.softwarinos.platform.detekto_back.profiles.domain.services;

import org.softwarinos.platform.detekto_back.profiles.domain.model.entities.StoreVendorImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StoreVendorImageService {
    StoreVendorImage uploadImage(MultipartFile multipartFile) throws IOException;
    void deleteImage(StoreVendorImage storeVendorImage) throws IOException;
}
