package org.softwarinos.platform.detekto_back.iam.domain.services;



import org.softwarinos.platform.detekto_back.iam.domain.model.entities.Role;
import org.softwarinos.platform.detekto_back.iam.domain.model.queries.GetAllRolesQuery;
import org.softwarinos.platform.detekto_back.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query); // retorna todos los roles
    Optional<Role> handle(GetRoleByNameQuery query); // retorna un rol por nombre

}
