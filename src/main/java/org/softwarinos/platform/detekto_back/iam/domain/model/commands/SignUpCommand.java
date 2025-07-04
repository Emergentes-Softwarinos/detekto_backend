package org.softwarinos.platform.detekto_back.iam.domain.model.commands;



import org.softwarinos.platform.detekto_back.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String fullName, String email, String password, List<Role> roles) {

}
