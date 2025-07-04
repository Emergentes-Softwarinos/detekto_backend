package org.softwarinos.platform.detekto_back.profiles.infrastructure.persistence.jpa.repositories;

import org.softwarinos.platform.detekto_back.profiles.domain.model.aggregates.StoreVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreVendorRepository extends JpaRepository<StoreVendor, Long> {
    boolean existsByEmail(String email);
    Optional<StoreVendor> findByEmail(String email);
}
