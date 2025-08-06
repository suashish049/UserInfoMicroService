package com.swiggato.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiggato.user.service.dto.UserRequestDTO;
import com.swiggato.user.service.impl.UserInfoServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserInfoServiceImpl userService;

	@Autowired
	public UserController(UserInfoServiceImpl userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/registerUser", produces = "application/json")
	public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserRequestDTO userDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(userDto));
	}
}
