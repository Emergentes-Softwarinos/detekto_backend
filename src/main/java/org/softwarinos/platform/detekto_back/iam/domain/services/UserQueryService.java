package org.softwarinos.platform.detekto_back.iam.domain.services;



import org.softwarinos.platform.detekto_back.iam.domain.model.aggregates.User;
import org.softwarinos.platform.detekto_back.iam.domain.model.queries.GetAllUsersQuery;
import org.softwarinos.platform.detekto_back.iam.domain.model.queries.GetUserByEmailQuery;
import org.softwarinos.platform.detekto_back.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query); // retorna todos los usuarios
    Optional<User> handle(GetUserByIdQuery query); // retorna un usuario por id
    Optional<User> handle(GetUserByEmailQuery query); // retorna un usuario por username
}
