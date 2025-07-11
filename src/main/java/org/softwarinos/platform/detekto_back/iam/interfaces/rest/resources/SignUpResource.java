package org.softwarinos.platform.detekto_back.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(
    String fullName,
    String email,
    String password,
    List<String> roles
) {

}
