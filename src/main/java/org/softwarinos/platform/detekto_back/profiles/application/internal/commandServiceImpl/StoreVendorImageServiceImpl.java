package org.softwarinos.platform.detekto_back.profiles.application.internal.commandServiceImpl;

import org.softwarinos.platform.detekto_back.profiles.domain.model.entities.StoreVendorImage;
import org.softwarinos.platform.detekto_back.profiles.domain.services.StoreVendorImageService;
import org.softwarinos.platform.detekto_back.profiles.infrastructure.persistence.jpa.repositories.StoreVendorImageRepository;
import org.softwarinos.platform.detekto_back.shared.domain.services.CloudinaryCommandService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class StoreVendorImageServiceImpl implements StoreVendorImageService {

    private final CloudinaryCommandService cloudinaryCommandService;
    private final StoreVendorImageRepository storeVendorImageRepository;

    public StoreVendorImageServiceImpl(CloudinaryCommandService cloudinaryCommandService, StoreVendorImageRepository storeVendorImageRepository) {
        this.cloudinaryCommandService = cloudinaryCommandService;
        this.storeVendorImageRepository = storeVendorImageRepository;
    }

    @Override
    public StoreVendorImage uploadImage(MultipartFile multipartFile) throws IOException {
        Map uploadImage = cloudinaryCommandService.uploadImage(multipartFile);
        String imageUrl = (String) uploadImage.get("url");
        String imageId = (String) uploadImage.get("public_id");
        StoreVendorImage storeVendorImage = new StoreVendorImage(multipartFile.getOriginalFilename(), imageUrl, imageId);

        return storeVendorImageRepository.save(storeVendorImage);
    }

    @Override
    public void deleteImage(StoreVendorImage storeVendorImage) throws IOException {
        cloudinaryCommandService.deleteImage(storeVendorImage.getImageId());
        storeVendorImageRepository.delete(storeVendorImage);
    }
}
