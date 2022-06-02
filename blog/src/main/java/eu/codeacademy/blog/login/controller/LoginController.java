package eu.codeacademy.blog.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("login")
    public String openLoginPage() {
        return "/login/login";
    }
}
