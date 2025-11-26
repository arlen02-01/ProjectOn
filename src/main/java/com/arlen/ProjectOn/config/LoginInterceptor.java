package com.arlen.ProjectOn.config;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest req,HttpServletResponse res, Object h) throws IOException {
		if(req.getSession().getAttribute("LOGIN_USER") == null) {
			res.sendRedirect("/auth/login?returnTo=" + req.getRequestURL());
			return false;
		}
		return true;
	}
}
