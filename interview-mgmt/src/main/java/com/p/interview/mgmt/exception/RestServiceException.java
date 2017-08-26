package com.p.interview.mgmt.exception;


// TODO: Auto-generated Javadoc
/**
 * The Class ZettaException.
 */
public class RestServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3130846185496715549L;


	/** The err msg. */
	private String errCode;

	/**
	 * Instantiates a new zetta exception.
	 *
	 * @param errCode the err code
	 */
	public RestServiceException(String errCode) {
		this.errCode = errCode;
	}

	
	/**
	 * Instantiates a new zetta exception.
	 */
	public RestServiceException() {
		super();
	}

	/**
	 * Instantiates a new zetta exception.
	 *
	 * @param e the e
	 */
	public RestServiceException(Throwable e) {
		super(e);
	}

	
	public RestServiceException(String errCode,String errorMessage) {
		super(errorMessage);
		this.errCode = errCode;
		
	}
	
	public RestServiceException(String errCode,String errorMessage,Throwable cause) {
		super(errorMessage,cause);
		this.errCode = errCode;
		
	}
	/**
	 * Gets the err code.
	 *
	 * @return the err code
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * Sets the err code.
	 *
	 * @param errCode the new err code
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

}
