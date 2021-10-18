package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

		System.out.println(userRepo.findByUserEmail(user.getUserEmail()));

		if (userRepo.findByUserEmail(user.getUserEmail()) != null) {
			res.setData(user);
			res.setMessage(HttpStatus.MULTI_STATUS.getReasonPhrase()+", user already exist!");
			res.setStatus(HttpStatus.MULTI_STATUS.value());
			return res;
		}

		if (!roleRepo.existsById(user.getRole().getRoleId())) {
			res.setData(user);
			res.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase() + " Role that use is invalid");
			res.setStatus(HttpStatus.NO_CONTENT.value());
			return res;
		}

		Optional<RoleEntity> roleData = null;

		if (roleRepo.existsById(user.getRole().getRoleId())) {
			roleData = roleRepo.findById(user.getRole().getRoleId());
		}

		if (roleData != null)
			user.setRole(roleData.get());

		UserEntity userEnt = userRepo.save(user);

		if (userEnt != null) {
			res.setData(userEnt);
			res.setMessage(HttpStatus.CREATED.getReasonPhrase());
			res.setStatus(HttpStatus.CREATED.value());
		} else {
			res.setData(null);
			res.setMessage(HttpStatus.NOT_MODIFIED.getReasonPhrase()+", user not found");
			res.setStatus(HttpStatus.NOT_MODIFIED.value());
		}
		return res;
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseBean<UserEntity> deleteUser(@PathVariable("id") String userIdString){
		ResponseBean<UserEntity> res = new ResponseBean<>();
		long userId = Long.valueOf(userIdString);
		
		UserEntity userData = userRepo.getById(userId);
		if(userData!=null) {
			userRepo.deleteById(userId);
			
			res.setData(userData);
			res.setMessage(HttpStatus.FOUND.getReasonPhrase()+", user delete!");
			res.setStatus(HttpStatus.FOUND.value());
		}else {
			res.setData(null);
			res.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase()+", user not Available!");
			res.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return res;
		
	}

	
}
