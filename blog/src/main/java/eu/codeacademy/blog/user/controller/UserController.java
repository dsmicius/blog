package eu.codeacademy.blog.user.controller;

import eu.codeacademy.blog.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String getUserForm(Model model) {
        model.addAttribute("userDto", UserDto.builder().build());

        return "/user/user";
    }

    @PostMapping
    public String register(@Valid UserDto userDto, BindingResult errors) {
        if (errors.hasErrors()) {
            return "/user/user";
        }

        return "redirect:/user";
    }
}
