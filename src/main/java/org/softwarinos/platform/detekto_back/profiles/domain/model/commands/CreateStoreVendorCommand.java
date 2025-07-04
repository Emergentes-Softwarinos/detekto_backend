package org.softwarinos.platform.detekto_back.profiles.domain.model.commands;

public record CreateStoreVendorCommand(
        String username,
        String email,
        String description,
        String gender,
        String age,
        String password)
{
}


