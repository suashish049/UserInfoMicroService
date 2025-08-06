package com.swiggato.user.service.dto;

import java.time.LocalDate;
import java.util.List;

import com.swiggato.user.model.Address;
import com.swiggato.user.model.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String passwordHash;
	private String profilePictureUrl;
	private String gender;
	private LocalDate dob;
	private String preferences;
	private List<Address> address;
	private List<Role> roles;
}
