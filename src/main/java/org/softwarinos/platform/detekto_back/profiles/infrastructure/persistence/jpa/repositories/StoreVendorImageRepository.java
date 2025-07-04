package org.softwarinos.platform.detekto_back.profiles.infrastructure.persistence.jpa.repositories;

import org.softwarinos.platform.detekto_back.profiles.domain.model.entities.StoreVendorImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreVendorImageRepository extends JpaRepository<StoreVendorImage, Long> {
}
