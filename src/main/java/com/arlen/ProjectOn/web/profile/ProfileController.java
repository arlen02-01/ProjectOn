package com.arlen.ProjectOn.web.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/{id}")
    public String profile(@PathVariable Long id) {
        // id 기반으로 나중에 사용자 페이지 띄움
        return "dev-in-progress";
    }
}

