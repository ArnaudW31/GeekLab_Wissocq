package com.geeklab.api.dto;

public record RegisterRequest(
        String email,
        String password
) {}
