/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.p.interview.mgmt.pojo;

import java.util.Date;

/**
 *
 * @author PREMENDRA
 */
public abstract class AbstractDTO {
	
	protected Date dateCreated;//creation_date
	protected Date dateLastModified;//last_updation_date
//	private boolean personal;
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateLastModified() {
		return dateLastModified;
	}
	public void setDateLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}
    
}
