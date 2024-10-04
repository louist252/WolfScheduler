package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Conflict interface
 * @author Louis Ton
 */
public interface Conflict {

	/**
	 * The abstract method that will throw an exception when there is a conflict
	 * @param possibleConflictingActivity The activiy 
	 * @throws ConflictException any conflicts
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
