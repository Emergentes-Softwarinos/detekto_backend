package org.softwarinos.platform.detekto_back.profiles.application.internal.eventHandlers.eventListeners;

import org.softwarinos.platform.detekto_back.iam.domain.model.events.UserRegisteredEvent;
import org.softwarinos.platform.detekto_back.profiles.domain.model.commands.CreateStoreVendorCommand;
import org.softwarinos.platform.detekto_back.profiles.domain.model.events.StoreVendorCreatedEvent;
import org.softwarinos.platform.detekto_back.profiles.domain.services.StoreVendorCommandService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserRegisteredEventHandler {

    private final StoreVendorCommandService storeVendorCommandService;
    private final ApplicationEventPublisher eventPublisher;

    public UserRegisteredEventHandler(StoreVendorCommandService storeVendorCommandService, ApplicationEventPublisher eventPublisher) {
        this.storeVendorCommandService = storeVendorCommandService;
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        // crea un nuevo StoreVendor si el usuario se ha registrado correctamente
        try {
            CreateStoreVendorCommand createStoreVendorCommand = new CreateStoreVendorCommand(
                    event.getFullName(),
                    event.getEmail(),
                    "",  // Puedes ajustar esto según tus necesidades
                    "",         // Género por defecto
                    "",                     // Edad por defecto
                    event.getPassword()      // Usa la contraseña proporcionada en el evento
            );
            Long storeVendorId = storeVendorCommandService.createStoreVendor(createStoreVendorCommand);  // Obtén el ID del storeVendor recién creado

            // Una vez que se crea el storeVendor, publicar un nuevo evento que BC Crops pueda escuchar
            StoreVendorCreatedEvent storeVendorCreatedEvent = new StoreVendorCreatedEvent(this, storeVendorId, event.getEmail());  // Usa el ID en lugar de Strings
            eventPublisher.publishEvent(storeVendorCreatedEvent);

        } catch (IllegalArgumentException e) {
            System.out.println("StoreVendor with email " + event.getEmail() + " already exists. Skipping creation.");
        }
    }
}


