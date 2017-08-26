/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.pojo;

import java.util.Vector;


/**
 *
 * @author PREMENDRA
 */
public class QuestionDTO extends AbstractDTO{
    private int questionID;//ques_id
    private int linkedCatID;//linked_cat_id
    private String question;//ques
    
    private Vector<AnswerDTO> answers;
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public int getLinkedCatID() {
		return linkedCatID;
	}
	public void setLinkedCatID(int linkedCatID) {
		this.linkedCatID = linkedCatID;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Vector<AnswerDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(Vector<AnswerDTO> answers) {
		this.answers = answers;
	}
    
	
    

}
