package org.softwarinos.platform.detekto_back.iam.application.internal.queryServices;


import org.softwarinos.platform.detekto_back.iam.domain.model.aggregates.User;
import org.softwarinos.platform.detekto_back.iam.domain.model.queries.GetAllUsersQuery;
import org.softwarinos.platform.detekto_back.iam.domain.model.queries.GetUserByEmailQuery;
import org.softwarinos.platform.detekto_back.iam.domain.model.queries.GetUserByIdQuery;
import org.softwarinos.platform.detekto_back.iam.domain.services.UserQueryService;
import org.softwarinos.platform.detekto_back.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail(query.email());
    }

}
