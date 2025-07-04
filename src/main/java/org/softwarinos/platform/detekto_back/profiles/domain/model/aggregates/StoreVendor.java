package org.softwarinos.platform.detekto_back.profiles.domain.model.aggregates;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softwarinos.platform.detekto_back.profiles.domain.model.commands.CreateStoreVendorCommand;
import org.softwarinos.platform.detekto_back.profiles.domain.model.entities.StoreVendorImage;
import org.softwarinos.platform.detekto_back.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

/**
 * StoreVendor
 *
 * <p>
 *     This class represents a Store Vendor in the system.
 * </p>
 *
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreVendor extends AuditableAbstractAggregateRoot<StoreVendor> {

    private String username;

    private String email;

    private String phoneNumber;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "storeVendor_image_id", referencedColumnName = "id")
    private StoreVendorImage storeVendorImage;

    public StoreVendor(CreateStoreVendorCommand command) {
        this.username = command.username();
        this.email = command.email();
        this.phoneNumber = command.phoneNumber();
        this.password = command.password();
    }

}
