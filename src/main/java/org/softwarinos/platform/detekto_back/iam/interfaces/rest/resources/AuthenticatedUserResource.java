package org.softwarinos.platform.detekto_back.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(
    Long id,
    String email,
    String token
) {

}
