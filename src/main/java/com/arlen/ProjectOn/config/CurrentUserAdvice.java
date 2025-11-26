package com.arlen.ProjectOn.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CurrentUserAdvice {
    @ModelAttribute("currentUser")
    public String currentUser(HttpServletRequest req) {
        var s = req.getSession(false);
        if (s == null) return null;
        Object v = s.getAttribute("LOGIN_USER");
        return (v instanceof String str && !str.isBlank()) ? str : null;
    }
    
    @ModelAttribute("currentPath")
    public String currentPath(HttpServletRequest req) {
        return (req != null && req.getRequestURI() != null) ? req.getRequestURI() : "";
    }
}