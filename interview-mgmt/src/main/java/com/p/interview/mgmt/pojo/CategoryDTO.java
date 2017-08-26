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
public class CategoryDTO extends AbstractDTO{
    private int catID;//cat_id
    private String catgoryName;//cat_name
    
    private Vector<QuestionDTO> questions;
	public int getCatID() {
		return catID;
	}
	public void setCatID(int catID) {
		this.catID = catID;
	}
	public String getCatgoryName() {
		return catgoryName;
	}
	public void setCatgoryName(String catgoryName) {
		this.catgoryName = catgoryName;
	}
	public Vector<QuestionDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(Vector<QuestionDTO> questions) {
		this.questions = questions;
	}
    
    
    
    
}
