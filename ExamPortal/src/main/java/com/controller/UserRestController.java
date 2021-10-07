package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.repository.RoleRepository;
import com.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/users")
	public ResponseBean<List<UserEntity>> getAllUsers() {
		ResponseBean<List<UserEntity>> res = new ResponseBean<List<UserEntity>>();
		List<UserEntity> users = userRepo.findAll();
		if (!users.isEmpty()) {
			res.setData(users);
			res.setMessage(HttpStatus.FOUND.getReasonPhrase());
			res.setStatus(HttpStatus.FOUND.value());
		} else {
			res.setData(null);
			res.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
			res.setStatus(HttpStatus.NO_CONTENT.value());
		}
		return res;
	}

	@PostMapping("/signup")
	public ResponseBean<UserEntity> userSignUp(@RequestBody UserEntity user) {
		ResponseBean<UserEntity> res = new ResponseBean<UserEntity>();
		
		Optional<RoleEntity> roleData = null;
		
		if (roleRepo.existsById(user.getRole().getRoleId())) {
			roleData = roleRepo.findById(user.getRole().getRoleId());
		}
		
		if(roleData!=null)
			user.setRole(roleData.get());
		
		UserEntity userEnt = userRepo.save(user);
		
		if (userEnt != null) {
			res.setData(userEnt);
			res.setMessage(HttpStatus.CREATED.getReasonPhrase());
			res.setStatus(HttpStatus.CREATED.value());
		} else {
			res.setData(null);
			res.setMessage(HttpStatus.NOT_MODIFIED.getReasonPhrase());
			res.setStatus(HttpStatus.NOT_MODIFIED.value());
		}
		return res;
	}

	@PostMapping("/role")
	public ResponseBean<RoleEntity> createRole(@RequestBody RoleEntity role) {
		ResponseBean<RoleEntity> res = new ResponseBean<RoleEntity>();
		RoleEntity roleInsert = roleRepo.save(role);
		if (roleInsert != null) {
			res.setData(roleInsert);
			res.setMessage(HttpStatus.CREATED.getReasonPhrase());
			res.setStatus(HttpStatus.CREATED.value());
		} else {
			res.setData(null);
			res.setMessage(HttpStatus.NOT_MODIFIED.getReasonPhrase());
			res.setStatus(HttpStatus.NOT_MODIFIED.value());
		}

		return res;
	}

	@GetMapping("/role")
	public ResponseBean<List<RoleEntity>> getAllRoles() {
		ResponseBean<List<RoleEntity>> res = new ResponseBean<List<RoleEntity>>();
		List<RoleEntity> roles = roleRepo.findAll();
		if (!roles.isEmpty()) {
			res.setData(roles);
			res.setMessage(HttpStatus.FOUND.getReasonPhrase());
			res.setStatus(HttpStatus.FOUND.value());
		} else {
			res.setData(null);
			res.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
			res.setStatus(HttpStatus.NO_CONTENT.value());
		}
		return res;
	}
}
