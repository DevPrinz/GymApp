package com.gymapp.gymapi.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api")
    public String home() {
        return "Gym API is running!";
    }
}