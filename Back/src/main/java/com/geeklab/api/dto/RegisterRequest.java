package com.geeklab.api.dto;

public record RegisterRequest(
        String username,
        String email,
        String password
) {}
