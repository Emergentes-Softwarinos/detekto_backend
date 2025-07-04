package org.softwarinos.platform.detekto_back.profiles.domain.services;

import org.softwarinos.platform.detekto_back.profiles.domain.model.aggregates.StoreVendor;
import org.softwarinos.platform.detekto_back.profiles.domain.model.queries.GetAllStoreVendorsQuery;
import org.softwarinos.platform.detekto_back.profiles.domain.model.queries.GetStoreVendorByIdQuery;

import java.util.List;
import java.util.Optional;

public interface StoreVendorQueryService {
    List<StoreVendor> getAllStoreVendors(GetAllStoreVendorsQuery query);
    Optional<StoreVendor> getStoreVendorById(GetStoreVendorByIdQuery query);
}
