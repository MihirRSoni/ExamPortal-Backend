package com.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "user_master")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	private String userName;
	private String userEmail;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String userPassword;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "roleId")
	private RoleEntity role;
	
	private String userAuthtoken;
	private String status;
	
	private String userGender;
	
	private Date userDob;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date userCreatedat;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date userUpdatedat;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	} 

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public String getUserAuthtoken() {
		return userAuthtoken;
	}

	public void setUserAuthtoken(String userAuthtoken) {
		this.userAuthtoken = userAuthtoken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Date getUserDob() {
		return userDob;
	}

	public void setUserDob(Date userDob) {
		this.userDob = userDob;
	}

	public Date getUserCreatedat() {
		return userCreatedat;
	}

	public void setUserCreatedat(Date userCreatedat) {
		this.userCreatedat = userCreatedat;
	}

	public Date getUserUpdatedat() {
		return userUpdatedat;
	}

	public void setUserUpdatedat(Date userUpdatedat) {
		this.userUpdatedat = userUpdatedat;
	}
	
	
}
