package com.arlen.ProjectOn.domain.common;

import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

public class CurrentUserAdvice {
    @ModelAttribute("currentUser")
    public String currentUser(HttpServletRequest req) {
        var s = req.getSession(false);
        if (s == null) return null;
        Object v = s.getAttribute("LOGIN_USER");
        return (v instanceof String str && !str.isBlank()) ? str : null;
    }
}
