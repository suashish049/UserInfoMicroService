package com.swiggato.user.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

	@Id
	@UuidGenerator
	@Column(name = "role_id", columnDefinition = "UUID")
	private UUID roleId;

	@Column(name = "role_name", nullable = false, unique = true, length = 50)
	private String roleName;

	@ManyToMany(mappedBy = "roles")
	private List<UserInfo> userInfo;
}
