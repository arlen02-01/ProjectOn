package com.arlen.ProjectOn.web.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arlen.ProjectOn.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final UserService userService;
	
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("form", new SignupForm());
        return "auth/signup";
    }
    
    @PostMapping("/login")
    public String doLogin(HttpServletRequest req, @RequestParam(value = "returnTo",required=false) String returnTo){
        req.getSession().setAttribute("LOGIN_USER", "demo");
        return "redirect:" + (returnTo != null ? returnTo : "/");
     }
    @PostMapping("/signup")
    public String doSignUp(@Valid @ModelAttribute("form") SignupForm form
    		, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {return "/auth/signup";}
    	try {
            userService.signup(form.getUsername(), form.getPassword());
		} catch (Exception e) {
            bindingResult.rejectValue("username", "duplicate", e.getMessage());
            return "auth/signup";
        }
    	return "redirect:/auth/login";
    }
    @PostMapping("/logout")
    public String doLogout(HttpServletRequest req){
        req.getSession(false).invalidate();
        return "redirect:/";
     }

    @Data
    public static class SignupForm {
        @NotBlank(message = "아이디를 입력하세요.")
        private String username;
        @NotBlank(message = "비밀번호를 입력하세요.")
        private String password;
    }
}
