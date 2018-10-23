package com.cs.console.interfaces.controller;


import com.cs.console.infrastructure.annotations.RateLimitAspect;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    @RateLimitAspect(name = "a", permitsPerSecond = 10)
    public String home(Model model) {
        model.addAttribute("name", "world");
        return "home";
    }
}
