package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "result_master")
public class ResultEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long resultId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId")
	private UserEntity user;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "examId")
	private ExamEntity exam;
	
	private long result;
	
	private boolean status;

	public long getResultId() {
		return resultId;
	}

	public void setResultId(long resultId) {
		this.resultId = resultId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ExamEntity getExam() {
		return exam;
	}

	public void setExam(ExamEntity exam) {
		this.exam = exam;
	}

	public long getResult() {
		return result;
	}

	public void setResult(long result) {
		this.result = result;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}	
}
