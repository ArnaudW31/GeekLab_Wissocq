package com.geeklab.api.dto;

public record LoginRequest(
        String email,
        String password
) {}
