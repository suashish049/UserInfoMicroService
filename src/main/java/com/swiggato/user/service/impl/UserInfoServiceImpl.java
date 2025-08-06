package com.swiggato.user.service.impl;

import java.time.ZonedDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swiggato.user.dto.AuthRequestDto;
import com.swiggato.user.model.Address;
import com.swiggato.user.model.UserInfo;
import com.swiggato.user.repository.UserRepository;
import com.swiggato.user.service.UserInfoService;
import com.swiggato.user.service.dto.UserRequestDTO;

@Service
public class UserInfoServiceImpl implements UserInfoService, UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Lazy
	@Autowired
	public UserInfoServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Map<String, String> registerUser(UserRequestDTO request) {

		UserInfo user = UserInfo.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.email(request.getEmail()).phoneNumber(request.getPhoneNumber())
				.passwordHash(passwordEncoder.encode(request.getPasswordHash())).status("ACTIVE")
				.gender(request.getGender()).dob(request.getDob()).preferences(request.getPreferences())
				.roles(request.getRoles()).address(request.getAddress()).build();

		for (Address address : user.getAddress()) {
			address.setUserInfo(user);
		}

		UserInfo savedUser = userRepository.save(user);
		return Map.of("message", "User Created Successfully with user ID - " + savedUser.getUserId());
	}

	@Override
	public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
	}

	@Override
	public Map<String, String> resetPassword(AuthRequestDto authRequestDto) {
		UserInfo user = loadUserByUsername(authRequestDto.getUsername());
		user.setPasswordHash(passwordEncoder.encode(authRequestDto.getPassword()));
		user.setUpdatedAt(ZonedDateTime.now());
		UserInfo updatedUser = userRepository.save(user);
		return Map.of("message", "Password reset successfully for user: " + updatedUser.getEmail());

	}

	@Override
	public void updateLastLogin(String username) {
		UserInfo user = loadUserByUsername(username);
		user.setLastLogin(ZonedDateTime.now());
		userRepository.save(user);
	}
}
