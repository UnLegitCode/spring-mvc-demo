package ru.unlegit.springmvcdemo.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.unlegit.springmvcdemo.dto.UserForm;
import ru.unlegit.springmvcdemo.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.listUsers());

        return "users/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new UserForm(-1, "", ""));
        model.addAttribute("mode", "create");

        return "users/form";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("form") UserForm form,
            Model model, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("mode", "create");

            return "users/form";
        }

        try {
            userService.createUser(form);

            return "redirect:/users";
        } catch (IllegalStateException exception) {
            bindingResult.rejectValue("email", "duplicate", exception.getMessage());
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("mode", "create");

            return "users/form";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("form", userService.getUserFormById(id));
        model.addAttribute("mode", "edit");

        return "users/form";
    }

    @PostMapping("/{id}")
    public String update(
            @PathVariable int id,
            @Valid @ModelAttribute("form") UserForm form,
            BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("form", new UserForm(id, form.name(), form.email()));
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("mode", "edit");

            return "users/form";
        }

        try {
            userService.updateUser(id, form);

            return "redirect:/users";
        } catch (IllegalStateException exception) {
            bindingResult.rejectValue("email", "duplicate", exception.getMessage());
            model.addAttribute("form", new UserForm(id, form.name(), form.email()));
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("mode", "edit");

            return "users/form";
        }
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id) {
        userService.deleteUser(id);

        return "redirect:/users";
    }
}