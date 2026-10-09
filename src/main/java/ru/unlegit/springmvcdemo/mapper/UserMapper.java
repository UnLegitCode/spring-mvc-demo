package ru.unlegit.springmvcdemo.mapper;

import org.springframework.stereotype.Component;
import ru.unlegit.springmvcdemo.dto.UserDto;
import ru.unlegit.springmvcdemo.dto.UserForm;
import ru.unlegit.springmvcdemo.model.User;

import java.util.List;

@Component
public class UserMapper {

    public User formToModel(UserForm form) {
        return new User(form.name(), form.email());
    }

    public UserForm modelToForm(User model) {
        return new UserForm(model.getId(), model.getName(), model.getEmail());
    }

    public UserDto modelToDto(User model) {
        return new UserDto(model.getId(), model.getName(), model.getEmail(), model.getCreatedAt());
    }

    public List<UserDto> modelToDto(List<User> models) {
        return models.stream()
                .map(this::modelToDto)
                .toList();
    }
}