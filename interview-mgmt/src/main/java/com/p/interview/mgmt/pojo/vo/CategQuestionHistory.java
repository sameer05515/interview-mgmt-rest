package com.p.interview.mgmt.pojo.vo;

import java.util.Date;

public class CategQuestionHistory {
	
	private int id;
	private int linkedCatID;//linked_cat_id
	//private int questionID;
	private int questionID;
	private Date dateLastRead;
	private String action;
	
	public CategQuestionHistory(int id,int linkedCatID, int questionID, Date dateLastReed,String action) {
		super();
		this.id = id;
		this.questionID = questionID;
		this.dateLastRead = dateLastReed;
		this.setAction(action);
	}
	
	public CategQuestionHistory() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTopicId() {
		return questionID;
	}
	public void setTopicId(int topicId) {
		this.questionID = topicId;
	}

	public Date getDateLastRead() {
		return dateLastRead;
	}

	public void setDateLastRead(Date dateLastRead) {
		this.dateLastRead = dateLastRead;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	

}
