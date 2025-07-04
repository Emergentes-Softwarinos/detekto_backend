package org.softwarinos.platform.detekto_back.profiles.application.internal.queryCommandServiceImpl;

import org.softwarinos.platform.detekto_back.profiles.domain.model.aggregates.StoreVendor;
import org.softwarinos.platform.detekto_back.profiles.domain.model.queries.GetAllStoreVendorsQuery;
import org.softwarinos.platform.detekto_back.profiles.domain.model.queries.GetStoreVendorByIdQuery;
import org.softwarinos.platform.detekto_back.profiles.domain.services.StoreVendorQueryService;
import org.softwarinos.platform.detekto_back.profiles.infrastructure.persistence.jpa.repositories.StoreVendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreVendorQueryServiceImpl implements StoreVendorQueryService {

    private final StoreVendorRepository storeVendorRepository;

    @Autowired
    public StoreVendorQueryServiceImpl(StoreVendorRepository storeVendorRepository) {
        this.storeVendorRepository = storeVendorRepository;
    }

    @Override
    public List<StoreVendor> getAllStoreVendors(GetAllStoreVendorsQuery query) {
        return storeVendorRepository.findAll();
    }

    @Override
    public Optional<StoreVendor> getStoreVendorById(GetStoreVendorByIdQuery query) {
        return storeVendorRepository.findById(query.storeVendorId());
    }
}
