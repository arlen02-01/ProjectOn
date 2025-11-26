package com.arlen.ProjectOn.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arlen.ProjectOn.domain.user.User;
import com.arlen.ProjectOn.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public Long signup(String username, String rawPassword) {
		if(userRepository.existsByUsername(username)) {
			throw new IllegalArgumentException("이미 사용중인 아이디 입니다");
		}
		User user = User.builder()
				.username(username)
				.password(passwordEncoder.encode(rawPassword))
				.role("Role_USER")
				.build();
		return userRepository.save(user).getId();
	}
	
	@Transactional
	public User loadByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException("사용자를 찾을 수 없습니다"));
	}
}
