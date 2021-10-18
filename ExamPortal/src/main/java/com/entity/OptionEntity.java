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
@Table(name = "option_master")
public class OptionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long optionId;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "questionId",nullable = false)
	private QuestionEntity question;

	private String optionValue;
	
	private boolean optionIsCorrect;
	
	
	
	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public boolean isOptionIsCorrect() {
		return optionIsCorrect;
	}

	public void setOptionIsCorrect(boolean optionIsCorrect) {
		this.optionIsCorrect = optionIsCorrect;
	}

	public long getOptionId() {
		return optionId;
	}

	public void setOptionId(long optionId) {
		this.optionId = optionId;
	}

	public QuestionEntity getQuestion() {
		return question;
	}

	public void setQuestion(QuestionEntity question) {
		this.question = question;
	}
	
	
}
