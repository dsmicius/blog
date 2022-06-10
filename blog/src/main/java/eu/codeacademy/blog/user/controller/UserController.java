package eu.codeacademy.blog.user.controller;

import eu.codeacademy.blog.user.dto.UserDto;
import eu.codeacademy.blog.user.dto.UserRoleDto;
import eu.codeacademy.blog.user.service.UserRegistrationService;
import eu.codeacademy.blog.user.service.UserService;
import eu.codeacademy.blog.validator.spring.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private static final String REGISTER_ROOT_PATH = "/users";
    private static final String UPDATE_PATH = REGISTER_ROOT_PATH + "/update";
    private static final String DELETE_PATH = REGISTER_ROOT_PATH + "/delete";
    private static final String REGISTER_PATH = "/public" + REGISTER_ROOT_PATH + "/register";
    private final UserValidator validator;
    private final UserRegistrationService userRegistrationService;
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

        userRegistrationService.register(userDto);

        return "redirect:/login";
    }

    @GetMapping(REGISTER_ROOT_PATH)
    public String getUsers(Model model) {
        model.addAttribute("userRoleList",userService.getAllUsers());
        return "user/users";
    }

    @GetMapping(UPDATE_PATH)
    public String getUserUpdate(Model model, @RequestParam String email) {
        model.addAttribute("userDetails",userService.loadUserByUsername(email));
        return "user/userUpdate";
    }

    @PostMapping(UPDATE_PATH)
    public String update(UserRoleDto userRoleDto) {
            userService.updateUser(userRoleDto);
        return "user/userUpdate";
    }

    @PostMapping(DELETE_PATH)
    public String delete(@RequestParam String email, RedirectAttributes redirectAttributes) {
        userService.deleteUser(email);
        redirectAttributes.addFlashAttribute("messageSuccess", "User deleted success");
        return "redirect:" + REGISTER_ROOT_PATH;
    }

}
