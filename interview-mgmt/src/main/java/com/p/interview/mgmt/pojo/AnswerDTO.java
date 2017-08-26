/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.pojo;


/**
 *
 * @author PREMENDRA
 */
public class AnswerDTO extends AbstractDTO{
    private int ansID;//ans_id
    private int linkedQuesID;//linked_ques_id
    private String answer;//answer
    private int linkedCatID;//linked_cat_id
	public int getAnsID() {
		return ansID;
	}
	public void setAnsID(int ansID) {
		this.ansID = ansID;
	}
	public int getLinkedQuesID() {
		return linkedQuesID;
	}
	public void setLinkedQuesID(int linkedQuesID) {
		this.linkedQuesID = linkedQuesID;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getLinkedCatID() {
		return linkedCatID;
	}
	public void setLinkedCatID(int linkedCatID) {
		this.linkedCatID = linkedCatID;
	}
        
}
