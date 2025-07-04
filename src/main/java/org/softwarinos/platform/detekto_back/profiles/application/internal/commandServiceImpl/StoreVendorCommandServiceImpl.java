package org.softwarinos.platform.detekto_back.profiles.application.internal.commandServiceImpl;

import jakarta.transaction.Transactional;
import org.softwarinos.platform.detekto_back.profiles.domain.model.aggregates.StoreVendor;
import org.softwarinos.platform.detekto_back.profiles.domain.model.commands.CreateStoreVendorCommand;
import org.softwarinos.platform.detekto_back.profiles.domain.model.commands.UpdateStoreVendorCommand;
import org.softwarinos.platform.detekto_back.profiles.domain.model.entities.StoreVendorImage;
import org.softwarinos.platform.detekto_back.profiles.domain.model.events.StoreVendorCreatedEvent;
import org.softwarinos.platform.detekto_back.profiles.domain.services.StoreVendorCommandService;
import org.softwarinos.platform.detekto_back.profiles.domain.services.StoreVendorImageService;
import org.softwarinos.platform.detekto_back.profiles.infrastructure.persistence.jpa.repositories.StoreVendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StoreVendorCommandServiceImpl implements StoreVendorCommandService {

    private final StoreVendorRepository storeVendorRepository;
    private final ApplicationEventPublisher eventPublisher; // se usa para publicar eventos
    private final StoreVendorImageService storeVendorImageService;

    @Autowired
    public StoreVendorCommandServiceImpl(StoreVendorRepository storeVendorRepository, ApplicationEventPublisher applicationEventPublisher, ApplicationEventPublisher eventPublisher, StoreVendorImageService storeVendorImageService) {
        this.storeVendorRepository = storeVendorRepository;
        this.eventPublisher = eventPublisher;
        this.storeVendorImageService = storeVendorImageService;
    }

    @Override
    public Long createStoreVendor(CreateStoreVendorCommand command) {

        // Verificar si ya existe un storeVendor con el mismo email
        if (storeVendorRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("StoreVendor with email " + command.email() + " already exists");
        }

        StoreVendor storeVendor = new StoreVendor(command);
        storeVendorRepository.save(storeVendor);

        // Publicamos el evento
        StoreVendorCreatedEvent event = new StoreVendorCreatedEvent(this, storeVendor.getId(), storeVendor.getEmail());
        eventPublisher.publishEvent(event);

        return storeVendor.getId();
    }

    @Override
    public Optional<StoreVendor> updateStoreVendor(UpdateStoreVendorCommand command) {
        var storeVendor = storeVendorRepository.findById(command.storeVendorId()).orElseThrow(() -> new IllegalArgumentException("StoreVendor with id " + command.storeVendorId() + "doesn't exist!!"));

        if (command.username() != null && !command.username().isEmpty()) {
            storeVendor.setUsername(command.username());
        }
        if (command.description() != null && !command.description().isEmpty()) {
            storeVendor.setDescription(command.description());
        }
        if (command.gender() != null && !command.gender().isEmpty()) {
            storeVendor.setGender(command.gender());
        }
        if (command.age() != null && !command.age().isEmpty()) {
            storeVendor.setAge(command.age());
        }
        storeVendorRepository.save(storeVendor);

        return Optional.of(storeVendor);

    }

    @Override
    public void deleteStoreVendor(Long storeVendorId) {
        // Buscar el storeVendor
        var storeVendor = storeVendorRepository.findById(storeVendorId).orElseThrow(() -> new IllegalArgumentException("StoreVendor with id " + storeVendorId + "doesn't exist!!"));

        // verificar si el storeVendor tiene una imagen, eliminar de cloudinary
        if (storeVendor.getStoreVendorImage() != null) {
            try {
                storeVendorImageService.deleteImage(storeVendor.getStoreVendorImage());
            } catch (IOException e) {
                throw new IllegalArgumentException("Error while deleting storeVendor image: " + e.getMessage());
            }
        }

        storeVendorRepository.deleteById(storeVendorId);

    }

    @Override
    @Transactional
    public Optional<StoreVendor> UpdateStoreVendorImage(MultipartFile file, StoreVendor storeVendor) throws IOException {

        if (storeVendor.getStoreVendorImage() != null) {
            storeVendorImageService.deleteImage(storeVendor.getStoreVendorImage());
        }

        // Subir la imagen
        StoreVendorImage newImage = storeVendorImageService.uploadImage(file);
        storeVendor.setStoreVendorImage(newImage);

        return Optional.of(storeVendorRepository.save(storeVendor));
    }

    @Override
    public Optional<StoreVendor> deleteStoreVendorImage(Long storeVendorId) throws IOException {
        // Buscar el storeVendor
        StoreVendor storeVendor = storeVendorRepository.findById(storeVendorId).orElseThrow(() -> new IllegalArgumentException("StoreVendor with id " + storeVendorId + "doesn't exist!!"));

        // verificamos si el storeVendor tiene una imagen
        if (storeVendor.getStoreVendorImage() != null) {
            storeVendorImageService.deleteImage(storeVendor.getStoreVendorImage()); // eliminamos la imagen de cloudinary
            storeVendor.setStoreVendorImage(null); // eliminamos la imagen del storeVendor
            storeVendorRepository.save(storeVendor); // guardamos los cambios
        } else {
            throw new IllegalArgumentException("StoreVendor with id " + storeVendorId + " doesn't have an image!!");
        }
        return Optional.of(storeVendor);
    }
}
