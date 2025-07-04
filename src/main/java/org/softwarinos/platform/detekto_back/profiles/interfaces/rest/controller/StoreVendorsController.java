package org.softwarinos.platform.detekto_back.profiles.interfaces.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.softwarinos.platform.detekto_back.profiles.domain.model.aggregates.StoreVendor;
import org.softwarinos.platform.detekto_back.profiles.domain.model.queries.GetAllStoreVendorsQuery;
import org.softwarinos.platform.detekto_back.profiles.domain.model.queries.GetStoreVendorByIdQuery;
import org.softwarinos.platform.detekto_back.profiles.domain.services.StoreVendorCommandService;
import org.softwarinos.platform.detekto_back.profiles.domain.services.StoreVendorQueryService;
import org.softwarinos.platform.detekto_back.profiles.interfaces.rest.resources.CreateStoreVendorResource;
import org.softwarinos.platform.detekto_back.profiles.interfaces.rest.resources.StoreVendorResource;
import org.softwarinos.platform.detekto_back.profiles.interfaces.rest.resources.UpdateStoreVendorResource;
import org.softwarinos.platform.detekto_back.profiles.interfaces.rest.transform.CreateStoreVendorResourceCommandFromResourceAssembler;
import org.softwarinos.platform.detekto_back.profiles.interfaces.rest.transform.StoreVendorResourceFromEntityAssembler;
import org.softwarinos.platform.detekto_back.profiles.interfaces.rest.transform.UpdateStoreVendorResourceCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/store-vendors", produces = "application/json")
@Tag(name = "Store Vendors", description = "Store Vendors API")
@CrossOrigin(origins = "*")
public class StoreVendorsController {

    private final StoreVendorCommandService storeVendorCommandService;
    private final StoreVendorQueryService storeVendorQueryService;


    public StoreVendorsController(StoreVendorCommandService storeVendorCommandService, StoreVendorQueryService storeVendorQueryService) {
        this.storeVendorCommandService = storeVendorCommandService;
        this.storeVendorQueryService = storeVendorQueryService;
    }

    @Operation(
            summary = "Create Store Vendor",
            description = "Create a new Store Vendor with the given data. The image is optional and can be uploaded later."
    )
    @PostMapping
    public ResponseEntity<StoreVendorResource> createStoreVendor(@Valid @RequestBody CreateStoreVendorResource createStoreVendorResource) {

        var createStoreVendorCommand = CreateStoreVendorResourceCommandFromResourceAssembler.toCommandFromResource(createStoreVendorResource);
        var storeVendorId = storeVendorCommandService.createStoreVendor(createStoreVendorCommand);
        if (storeVendorId == null) {
            return ResponseEntity.badRequest().build();
        }

        var getStoreVendorByIdQuery = new GetStoreVendorByIdQuery(storeVendorId);
        var storeVendor = storeVendorQueryService.getStoreVendorById(getStoreVendorByIdQuery);
        if (storeVendor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var storeVendorResource = StoreVendorResourceFromEntityAssembler.toResourceFromEntity(storeVendor.get());
        return new ResponseEntity<>(storeVendorResource, HttpStatus.CREATED);

    }

    @Operation(
            summary = "Get Store Vendor by ID",
            description = "Get a Store Vendor by its ID. Returns a 404 Not Found if the Store Vendor does not exist."
    )
    @GetMapping("/{storeVendorId}")
    public ResponseEntity<StoreVendorResource> getStoreVendorById(@PathVariable Long storeVendorId) {
        var getStoreVendorByIdQuery = new GetStoreVendorByIdQuery(storeVendorId);
        var storeVendor = storeVendorQueryService.getStoreVendorById(getStoreVendorByIdQuery);
        if (storeVendor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var storeVendorResource = StoreVendorResourceFromEntityAssembler.toResourceFromEntity(storeVendor.get());
        return ResponseEntity.ok(storeVendorResource);
    }

    @Operation(
            summary = "Get all Store Vendors",
            description = "Get all Store Vendors. Returns an empty list if there are no Store Vendors."
    )
    @GetMapping
    public ResponseEntity<List<StoreVendorResource>> getAllStoreVendors() {
        var getAllStoreVendorsQuery = new GetAllStoreVendorsQuery();
        var storeVendors = storeVendorQueryService.getAllStoreVendors(getAllStoreVendorsQuery);
        return ResponseEntity.ok(storeVendors.stream().map(StoreVendorResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList()));
    }


    @Operation(
            summary = "Update Store Vendor",
            description = "Update a Store Vendor by its ID. Returns a 404 Not Found if the Store Vendor does not exist."
    )
    @PutMapping("/{storeVendorId}")
    public ResponseEntity<StoreVendorResource> updateStoreVendor(@Valid @PathVariable Long storeVendorId, @RequestBody UpdateStoreVendorResource updateStoreVendorResource) {
        var updateStoreVendorCommand = UpdateStoreVendorResourceCommandFromResourceAssembler.toCommandFromResource(updateStoreVendorResource, storeVendorId);
        var updatedStoreVendor = storeVendorCommandService.updateStoreVendor(updateStoreVendorCommand);
        if (updatedStoreVendor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var storeVendorResource = StoreVendorResourceFromEntityAssembler.toResourceFromEntity(updatedStoreVendor.get());
        return ResponseEntity.ok(storeVendorResource);
    }

    @Operation(
            summary = "Update Store Vendor Image",
            description = "Update the image of a Store Vendor by its ID. The image is optional and can be uploaded later."
    )
    @PutMapping(value = "/{storeVendorId}/storeVendorImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StoreVendorResource> updateStoreVendorImage(
            @PathVariable Long storeVendorId,
            @RequestPart("file")MultipartFile file) throws IOException {

        // buscamos el id
        Optional<StoreVendor> storeVendorOptional = storeVendorQueryService.getStoreVendorById(new GetStoreVendorByIdQuery(storeVendorId));


        // si no existe el store vendor
        if (storeVendorOptional.isEmpty()) {
            return ResponseEntity.notFound().header("message", "StoreVendor with id " + storeVendorId + " not found").build();
        }

        StoreVendor storeVendor = storeVendorOptional.get();

        // actualizamos la imagen
        Optional<StoreVendor> updatedStoreVendorOptional = storeVendorCommandService.UpdateStoreVendorImage(file, storeVendor);

        // si no se pudo actualizar la imagen
        if (updatedStoreVendorOptional.isEmpty()) {
            return ResponseEntity.badRequest().header("message", "Error while updating storeVendor image").build();
        }

        // retornamos el store vendor actualizado
        return ResponseEntity.ok(StoreVendorResourceFromEntityAssembler.toResourceFromEntity(updatedStoreVendorOptional.get()));

    }

    @Operation(
            summary = "Delete Store Vendor",
            description = "Delete a Store Vendor by its ID. Returns a 404 Not Found if the Store Vendor does not exist."
    )
    @DeleteMapping("/{storeVendorId}")
    public ResponseEntity<?> deleteStoreVendor(@PathVariable Long storeVendorId) {
        storeVendorCommandService.deleteStoreVendor(storeVendorId);
        return ResponseEntity.ok("StoreVendor with given id successfully deleted");
    }

    @Operation(
            summary = "Delete Store Vendor Image",
            description = "Delete the image of a Store Vendor by its ID. Returns a 404 Not Found if the Store Vendor does not exist."
    )
    @DeleteMapping("/{storeVendorId}/storeVendorImage")
    public ResponseEntity<StoreVendorResource> deleteStoreVendorImage(@PathVariable Long storeVendorId) throws IOException {

        // delegar la eliminación de la imagen al servicio
        Optional<StoreVendor> updatedStoreVendorOptional = storeVendorCommandService.deleteStoreVendorImage(storeVendorId);

        // si no se pudo eliminar la imagen
        if (updatedStoreVendorOptional.isEmpty()) {
            return ResponseEntity.badRequest().header("message", "Error while deleting storeVendor image").build();
        }

        // retornamos el store vendor actualizado
        StoreVendor updatedStoreVendor = updatedStoreVendorOptional.get();

        return ResponseEntity.ok(StoreVendorResourceFromEntityAssembler.toResourceFromEntity(updatedStoreVendor));
    }
}
