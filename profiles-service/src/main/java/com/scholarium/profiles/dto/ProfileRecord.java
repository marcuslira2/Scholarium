package com.scholarium.profiles.dto;

import com.scholarium.profiles.enums.RoleEnum;

public record ProfileRecord(
        String name,
        String cpf,
        String address,
        String photo,
        RoleEnum role
) {
}
