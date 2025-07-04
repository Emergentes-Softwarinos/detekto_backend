package org.softwarinos.platform.detekto_back.profiles.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StoreVendorCreatedEvent extends ApplicationEvent {

    private final Long storeVendorId;
    private final String email;

    public StoreVendorCreatedEvent(Object source, Long storeVendorId, String email) {
        super(source);
        this.storeVendorId = storeVendorId;
        this.email = email;
    }
}
