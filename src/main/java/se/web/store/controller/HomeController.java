package se.web.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "admin/login.html";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout.html";
    }


}
