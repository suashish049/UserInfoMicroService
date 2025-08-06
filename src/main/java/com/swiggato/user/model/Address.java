package com.swiggato.user.model;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

	@Id
	@UuidGenerator
	@Column(name = "address_id", columnDefinition = "UUID")
	private UUID addressId;

	@Column(length = 255)
	private String street;

	@Column(length = 100)
	private String city;

	@Column(length = 100)
	private String state;

	@Column(name = "postal_code", length = 20)
	private String postalCode;

	@Column(length = 100)
	private String country;

	@Builder.Default
	@Column(name = "created_at", nullable = false)
	private ZonedDateTime createdAt = ZonedDateTime.now();

	@Column(name = "updated_at")
	private ZonedDateTime updatedAt;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;
}
