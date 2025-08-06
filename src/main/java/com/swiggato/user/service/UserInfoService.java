package com.swiggato.user.service;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.swiggato.user.dto.AuthRequestDto;
import com.swiggato.user.service.dto.UserRequestDTO;

@Repository
public interface UserInfoService {

	Map<String, String> registerUser(UserRequestDTO request);

	Map<String, String> resetPassword(AuthRequestDto authRequestDto);

	void updateLastLogin(String username);

}
