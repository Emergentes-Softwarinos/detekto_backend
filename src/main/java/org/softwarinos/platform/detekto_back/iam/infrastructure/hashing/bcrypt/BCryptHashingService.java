package org.softwarinos.platform.detekto_back.iam.infrastructure.hashing.bcrypt;


import org.softwarinos.platform.detekto_back.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}
