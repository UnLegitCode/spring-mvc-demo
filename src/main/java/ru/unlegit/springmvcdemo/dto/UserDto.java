package ru.unlegit.springmvcdemo.dto;

import java.time.LocalDateTime;

public record UserDto(int id, String name, String email, LocalDateTime createdAt) {}