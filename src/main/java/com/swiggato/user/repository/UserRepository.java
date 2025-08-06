package com.swiggato.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swiggato.user.model.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, UUID> {

	Optional<UserInfo> findByEmail(String email);

}
