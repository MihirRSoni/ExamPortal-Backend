package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.RoleEntity;
import com.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public UserEntity findByUserEmail(String userEmail);
	public List<UserEntity> findByRole(RoleEntity roleId);
	public void deleteAllByRole(RoleEntity roleId);
}
