package ru.unlegit.springmvcdemo.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserForm(
        @Nullable
        Integer id,

        @NotBlank(message = "Имя обязательно")
        @Size(min = 2, max = 50, message = "Длина имени должна быть 2-50 символов")
        String name,

        @Email(message = "Некорректая электронная почта")
        @NotBlank(message = "Электронная почта обязательная")
        String email
) {}