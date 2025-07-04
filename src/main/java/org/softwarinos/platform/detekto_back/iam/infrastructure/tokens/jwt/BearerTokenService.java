package org.softwarinos.platform.detekto_back.iam.infrastructure.tokens.jwt;


import jakarta.servlet.http.HttpServletRequest;
import org.softwarinos.platform.detekto_back.iam.application.internal.outboundservices.tokens.TokenService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;

public interface BearerTokenService extends TokenService {

    String getBearerTokenFrom(HttpServletRequest request);
    String generateToken(Authentication authentication);

}
