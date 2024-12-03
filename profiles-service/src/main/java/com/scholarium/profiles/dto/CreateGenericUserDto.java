package com.scholarium.profiles.dto;

public record CreateGenericUserDto(
        String name,
        String login,
        String password,
        String cpf,
        String address,
        String photo,
        String role

) {
}
