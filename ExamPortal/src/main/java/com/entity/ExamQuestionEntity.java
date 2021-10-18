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
@Table(name = "exam_question")
public class ExamQuestionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long examQuestionId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "examId")
	private ExamEntity exam;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "questionId")
	private QuestionEntity question;

	public long getExamQuestionId() {
		return examQuestionId;
	}

	public void setExamQuestionId(long examQuestionId) {
		this.examQuestionId = examQuestionId;
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
}
