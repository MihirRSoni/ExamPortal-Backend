package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.RoleEntity;
import com.entity.UserEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	public RoleEntity findByRoleName(String roleName);
	public Boolean existsByRoleName(String roleName);
}
