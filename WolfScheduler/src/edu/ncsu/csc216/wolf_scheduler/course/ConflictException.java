package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The ConflictException class
 * @author Louis Ton
 */
public class ConflictException extends Exception {
	
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The ConflictException contructor with the message as the parameter
	 * @param message the message for the conflict
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * The parameterless ConflictException constructor
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
