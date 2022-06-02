package eu.codeacademy.blog.user.controller;

import eu.codeacademy.blog.user.dto.UserDto;
import eu.codeacademy.blog.user.service.UserService;
import eu.codeacademy.blog.validator.spring.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private static final String REGISTER_ROOT_PATH = "/users";
    private static final String REGISTER_PATH = "/public" + REGISTER_ROOT_PATH + "/register";
    private final UserValidator validator;
    private final UserService userService;

    @GetMapping(REGISTER_PATH)
    public String getUserForm(Model model) {
        model.addAttribute("userDto", UserDto.builder().build());

        return "/user/user";
    }

    @PostMapping(REGISTER_PATH)
    public String register(@Valid UserDto userDto, BindingResult errors) {
        validator.validate(userDto, errors);
        if (errors.hasErrors()) {
            return "/user/user";
        }

        userService.register(userDto);

        return "redirect:/users";
    }
}
