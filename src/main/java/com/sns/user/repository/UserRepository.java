package com.sns.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	public Optional<UserEntity> findByLoginId(String loginId);
	public Optional<UserEntity> findByLoginIdAndPassword(String loginId, String password);
}
