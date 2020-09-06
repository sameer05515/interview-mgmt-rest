package com.p.interview.mgmt.pojo.vo;

import java.util.Date;

public class CategQuestionHistory {
	
	private int id;
	private int topicId;
	private Date dateLastRead;
	private String action;
	
	public CategQuestionHistory(int id, int topicId, Date dateLastReed,String action) {
		super();
		this.id = id;
		this.topicId = topicId;
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
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
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
