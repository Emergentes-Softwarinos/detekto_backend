package org.softwarinos.platform.detekto_back.iam.infrastructure.authorization.sfs.services;


import org.softwarinos.platform.detekto_back.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import org.softwarinos.platform.detekto_back.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public  UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));
        return UserDetailsImpl.build(user);
    }

}
