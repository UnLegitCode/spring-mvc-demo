package ru.unlegit.springmvcdemo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.unlegit.springmvcdemo.dto.UserDto;
import ru.unlegit.springmvcdemo.dto.UserForm;
import ru.unlegit.springmvcdemo.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> listUsers() {
        return userService.listUsers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserForm form) {
        return userService.createUser(form);
    }

    @PutMapping("/{id}")
    public UserDto editUser(@PathVariable int id, @RequestBody UserForm form) {
        return userService.updateUser(id, form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}