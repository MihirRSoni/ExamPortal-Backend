package com.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "exam_master")
@EntityListeners(AuditingEntityListener.class)
public class ExamEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long examId;
	
	private String examName;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "subjectId")
	private SubjectEntity subject;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "topicId")
	private TopicEntity topic;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId")
	private UserEntity user;
	
	
	private boolean examIsPrivate;
	private String examVarificationCode;
	private Date examStartDate;
	private Date examEndDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date examCreatedatDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date examUpdatedatDate;

	public long getExamId() {
		return examId;
	}

	public void setExamId(long examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}

	public TopicEntity getTopic() {
		return topic;
	}

	public void setTopic(TopicEntity topic) {
		this.topic = topic;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public boolean isExamIsPrivate() {
		return examIsPrivate;
	}

	public void setExamIsPrivate(boolean examIsPrivate) {
		this.examIsPrivate = examIsPrivate;
	}

	public String getExamVarificationCode() {
		return examVarificationCode;
	}

	public void setExamVarificationCode(String examVarificationCode) {
		this.examVarificationCode = examVarificationCode;
	}

	public Date getExamStartDate() {
		return examStartDate;
	}

	public void setExamStartDate(Date examStartDate) {
		this.examStartDate = examStartDate;
	}

	public Date getExamEndDate() {
		return examEndDate;
	}

	public void setExamEndDate(Date examEndDate) {
		this.examEndDate = examEndDate;
	}

	public Date getExamCreatedatDate() {
		return examCreatedatDate;
	}

	public void setExamCreatedatDate(Date examCreatedatDate) {
		this.examCreatedatDate = examCreatedatDate;
	}

	public Date getExamUpdatedatDate() {
		return examUpdatedatDate;
	}

	public void setExamUpdatedatDate(Date examUpdatedatDate) {
		this.examUpdatedatDate = examUpdatedatDate;
	}
	
	
}
