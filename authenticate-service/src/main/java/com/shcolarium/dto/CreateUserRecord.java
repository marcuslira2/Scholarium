package com.shcolarium.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRecord(@NotBlank String login, @NotBlank String password) {
}
