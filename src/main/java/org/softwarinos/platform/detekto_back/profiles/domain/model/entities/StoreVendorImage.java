package org.softwarinos.platform.detekto_back.profiles.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softwarinos.platform.detekto_back.shared.domain.model.entities.AuditableModel;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StoreVendorImage extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imageUrl;

    private String imageId;

    public StoreVendorImage(String name, String imageUrl, String imageId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }

}
