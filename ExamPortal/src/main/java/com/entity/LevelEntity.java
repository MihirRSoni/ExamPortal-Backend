package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "level_master")
public class LevelEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long lavelId;
	
	private String level;

	public long getLavelId() {
		return lavelId;
	}

	public void setLavelId(long lavelId) {
		this.lavelId = lavelId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
