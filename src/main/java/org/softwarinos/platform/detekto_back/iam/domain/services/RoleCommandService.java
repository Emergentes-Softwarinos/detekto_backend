package org.softwarinos.platform.detekto_back.iam.domain.services;


import org.softwarinos.platform.detekto_back.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
