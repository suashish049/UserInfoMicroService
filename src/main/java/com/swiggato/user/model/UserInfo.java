package com.swiggato.user.model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo implements UserDetails {

	@Id
	@UuidGenerator
	@Column(name = "user_id", columnDefinition = "UUID")
	private UUID userId;

	@Column(name = "first_name", nullable = false, length = 100)
	private String firstName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Column(nullable = false, unique = true, length = 255)
	private String email;

	@Column(name = "phone_number", unique = true, length = 20)
	private String phoneNumber;

	@Column(name = "password_hash", nullable = false)
	private String passwordHash;

	@Column(name = "profile_picture_url")
	private String profilePictureUrl;

	@Column(length = 10)
	private String gender;

	private LocalDate dob;

	@Builder.Default
	@Column(nullable = false)
	private String status = "ACTIVE";

	@Builder.Default
	@Column(name = "email_verified", nullable = false)
	private boolean emailVerified = false;

	@Builder.Default
	@Column(name = "phone_verified", nullable = false)
	private boolean phoneVerified = false;

	@Builder.Default
	@Column(name = "created_at", nullable = false)
	private ZonedDateTime createdAt = ZonedDateTime.now();

	@Column(name = "updated_at")
	private ZonedDateTime updatedAt;

	@Column(name = "last_login")
	private ZonedDateTime lastLogin;

	private String preferences;

	@OneToMany(mappedBy = "userInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Address> address;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName().trim().toUpperCase()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return getPasswordHash();
	}

	@Override
	public String getUsername() {
		return getEmail();
	}
}
