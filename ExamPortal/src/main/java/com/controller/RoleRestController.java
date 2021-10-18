package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.repository.RoleRepository;
import com.repository.UserRepository;

@RestController
@RequestMapping("/role")
public class RoleRestController {

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private UserRepository userRepo;

	@PostMapping("/role")
	public ResponseBean<RoleEntity> createRole(@RequestBody RoleEntity role) {
		ResponseBean<RoleEntity> res = new ResponseBean<RoleEntity>();
		if (Boolean.valueOf(roleRepo.existsByRoleName(role.getRoleName()))
				|| Boolean.valueOf(roleRepo.existsById(role.getRoleId()))) {
			res.setData(null);
			res.setMessage(HttpStatus.MULTI_STATUS.getReasonPhrase() + ", role already exist");
			res.setStatus(HttpStatus.MULTI_STATUS.value());
			return res;
		}
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

	@DeleteMapping("/role/{roleId}")
	public ResponseBean<String> deleteRole(@PathVariable("roleId") String roleString) {
		long role = Long.valueOf(roleString);

		ResponseBean<String> res = new ResponseBean<String>();

		RoleEntity roleData = roleRepo.getById(role);
		if (role > 0 && roleRepo.existsById(role)) {

			List<UserEntity> usersWithRole = userRepo.findByRole(roleData);
			userRepo.deleteAll(usersWithRole);
			roleRepo.deleteById(role);

			res.setData("role deleted!");
			res.setMessage(HttpStatus.OK.getReasonPhrase());
			res.setStatus(HttpStatus.OK.value());
		} else {
			res.setData("Role is not found!");
			res.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase() + ", role id is invalid! ");
			res.setStatus(HttpStatus.NO_CONTENT.value());
		}
		return res;
	}

	@GetMapping("/role/{roleId}")
	public ResponseBean<RoleEntity> getRoleById(@PathVariable("roleId") String roleString) {
		long role = Long.valueOf(roleString);

		ResponseBean<RoleEntity> res = new ResponseBean<>();

		if (role > 0 && roleRepo.existsById(role)) {

			RoleEntity roleData = roleRepo.getById(role);

			res.setData(roleData);
			res.setMessage(HttpStatus.FOUND.getReasonPhrase() + ", rolefound");
			res.setStatus(HttpStatus.FOUND.value());
		} else {
			res.setData(null);
			res.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase() + ", role id is invalid! ");
			res.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return res;
	}

	@PutMapping("/role")
	public ResponseBean<RoleEntity> updateRole(@RequestBody RoleEntity role) {
		ResponseBean<RoleEntity> res = new ResponseBean<>();
		if(role!=null && roleRepo.existsById(role.getRoleId())) {
			try {
				roleRepo.save(role);
			}catch (Exception e) {
				res.setData(null);
				res.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase() + ", role is invalid! ");
				res.setStatus(HttpStatus.NOT_FOUND.value());
				return res;
			}
			res.setData(role);
			res.setMessage(HttpStatus.ACCEPTED.getReasonPhrase() + ", role updated!");
			res.setStatus(HttpStatus.FOUND.value());
		}else {
			res.setData(null);
			res.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase() + ", role is invalid! ");
			res.setStatus(HttpStatus.NOT_FOUND.value());
		}
		return res;
	}

}
