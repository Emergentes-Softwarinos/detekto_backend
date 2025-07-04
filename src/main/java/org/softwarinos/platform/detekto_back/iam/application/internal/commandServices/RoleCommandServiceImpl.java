package org.softwarinos.platform.detekto_back.iam.application.internal.commandServices;


import org.softwarinos.platform.detekto_back.iam.domain.model.commands.SeedRolesCommand;
import org.softwarinos.platform.detekto_back.iam.domain.model.entities.Role;
import org.softwarinos.platform.detekto_back.iam.domain.model.valueobjects.Roles;
import org.softwarinos.platform.detekto_back.iam.domain.services.RoleCommandService;
import org.softwarinos.platform.detekto_back.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }

}
