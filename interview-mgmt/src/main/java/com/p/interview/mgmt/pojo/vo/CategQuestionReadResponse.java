package com.p.interview.mgmt.pojo.vo;

import java.util.ArrayList;
import java.util.List;

import com.p.interview.mgmt.pojo.QuestionDTO;



public class CategQuestionReadResponse {

	private int topicId;
	private QuestionDTO question;
	private int count = 0;
	private List<CategQuestionHistory> reads = new ArrayList<>();

	public CategQuestionReadResponse(int topicId, QuestionDTO topic, List<CategQuestionHistory> reads) {
		super();
		this.topicId = topicId;
		this.question = topic;
		if (reads != null && reads.size() > 0) {
			this.count = reads.size();

			this.reads = reads;
		}
	}

	public CategQuestionReadResponse() {
		super();
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public QuestionDTO getTopic() {
		return question;
	}

	public void setTopic(QuestionDTO topic) {
		this.question = topic;
	}

	public int getCount() {
		if (reads != null && reads.size() > 0)
			this.count=reads.size();
			
			return count;
	}

	// public void setCount(int count) {
	// this.count = count;
	// }
	public List<CategQuestionHistory> getReads() {
		return reads;
	}

	public void setReads(List<CategQuestionHistory> reads) {
		if (reads != null && reads.size() > 0)
			this.reads = reads;
	}

}
