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
@Table(name="user_exam")
public class UserExamEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userExamId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId")
	private UserEntity user;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "examId")
	private ExamEntity exam;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "questionId")
	private QuestionEntity question;
	
	private String answare;
	
	private boolean status;

	public long getUserExamId() {
		return userExamId;
	}

	public void setUserExamId(long userExamId) {
		this.userExamId = userExamId;
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

	public QuestionEntity getQuestion() {
		return question;
	}

	public void setQuestion(QuestionEntity question) {
		this.question = question;
	}

	public String getAnsware() {
		return answare;
	}

	public void setAnsware(String answare) {
		this.answare = answare;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
