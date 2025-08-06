package com.swiggato.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swiggato.user.dto.AuthRequestDto;
import com.swiggato.user.service.JwtService;
import com.swiggato.user.service.UserInfoService;

@RestController
public class LoginController {

	private final JwtService jwtService;
	private final UserInfoService userInfoService;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public LoginController(JwtService jwtService, UserInfoService userInfoService,
			AuthenticationManager authenticationManager) {
		this.jwtService = jwtService;
		this.userInfoService = userInfoService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<Object> authenticateandGenerateToken(@RequestBody AuthRequestDto authRequestDto) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
		if (authenticate.isAuthenticated()) {
			userInfoService.updateLastLogin(authRequestDto.getUsername());
			String token = jwtService.generateToken(authRequestDto.getUsername());
			return ResponseEntity.ok().header("Authorization", "Bearer " + token)
					.body("Authentication successful, token generated");
		} else {
			return ResponseEntity.status(401).body("Authentication failed");
		}
	}

	@PutMapping(value = "/resetPassword", produces = "application/json")
	public ResponseEntity<Object> resetPassword(@RequestBody AuthRequestDto authRequestDto) {
		Map<String, String> responseMap = userInfoService.resetPassword(authRequestDto);
		return ResponseEntity.ok(responseMap);
	}

}
