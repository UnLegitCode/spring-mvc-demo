package ru.unlegit.springmvcdemo.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.unlegit.springmvcdemo.dto.UserDto;
import ru.unlegit.springmvcdemo.dto.UserForm;
import ru.unlegit.springmvcdemo.mapper.UserMapper;
import ru.unlegit.springmvcdemo.model.User;
import ru.unlegit.springmvcdemo.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public List<UserDto> listUsers() {
        return userMapper.modelToDto(userRepository.findAll());
    }

    public UserForm getUserFormById(int id) {
        return userMapper.modelToForm(userRepository.findById(id).orElseThrow());
    }

    @Transactional
    public UserDto createUser(UserForm form) {
        if (userRepository.existsByEmail(form.email())) {
            throw new IllegalStateException("Электронная почта уже используется");
        }

        var user = userMapper.formToModel(form);

        return userMapper.modelToDto(userRepository.save(user));
    }

    @Transactional
    public UserDto updateUser(int id, UserForm form) {
        var user = userRepository.findById(id).orElseThrow();

        if (userRepository.existsByEmailAndIdNot(form.email(), id)) {
            throw new IllegalStateException("Электронная почта уже используется");
        }

        user.setName(form.name());
        user.setEmail(form.email());

        return userMapper.modelToDto(userRepository.save(user));
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}