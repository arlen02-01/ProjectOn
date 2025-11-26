package com.arlen.ProjectOn.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/__version")
    @ResponseBody
    public String version() {
        return "version-2025-11-26-01";
    }
    
    @GetMapping("/")
    public String home() {
        // templates/home.html 렌더링
        return "home";
    }
}
