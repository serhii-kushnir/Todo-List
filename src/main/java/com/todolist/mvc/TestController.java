package com.todolist.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
final class TestController {

    @GetMapping("/test")
    public String testMethod(final Model model) {
        model.addAttribute("message", "Hello, World");
        return "test";
    }
}
