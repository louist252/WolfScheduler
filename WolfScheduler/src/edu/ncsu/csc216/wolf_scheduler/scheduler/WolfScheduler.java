package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * WolfScheduler class
 * @author Louis Ton
 */
public class WolfScheduler {
	
	/** Course catalog */
	private ArrayList<Course> catalog = new ArrayList<Course>();
	/** Course schedule */
	private ArrayList<Activity> schedule = new ArrayList<Activity>();
	/** Schedule title */
	private String title;


	
	/**
	 * Construct a WolfScheduler object with a file
	 * @param fileName file to read the catalog from
	 * @throws IllegalArgumentException if the file is not found
	 */
	public WolfScheduler(String fileName) {
		this.schedule = new ArrayList<Activity>();
		this.title = "My Schedule";
		try {
			catalog.addAll(CourseRecordIO.readCourseRecords(fileName));
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot find file");
		}
	}

	/**
	 * Return a list of the classes in the catalog with their name, section, and title
	 * @return the catalog
	 */
	public String[][] getCourseCatalog() {
        String [][] catalogArray = new String[catalog.size()][3];
        for (int i = 0; i < catalog.size(); i++) {
            Course c = catalog.get(i);
            catalogArray[i] = c.getShortDisplayArray();
        }
        return catalogArray;
    }

	/**
	 * Return a list of classes in the schedule with their name, section, and title
	 * @return the schedule
	 */
	public String[][] getScheduledActivities() {
		String [][] catalogArray = new String[schedule.size()][4];
        for (int i = 0; i < schedule.size(); i++) {
            Activity c = schedule.get(i);
            catalogArray[i] = c.getShortDisplayArray();
        }
        return catalogArray;
	}
	

	/**
	 *  Return a list of classes in the schedule with all information
	 * @return the full schedule
	 */
	public String[][] getFullScheduledActivities() {
		String [][] catalogArray = new String[schedule.size()][7];
        for (int i = 0; i < schedule.size(); i++) {
            Activity c = schedule.get(i);
            catalogArray[i] = c.getLongDisplayArray();
        }
        return catalogArray;
	}

	/**
	 * Add a course to a schedule
	 * @param name name of the class
	 * @param section the class section
	 * @return true if a course has been sucessfully added, false otherwise
	 */
	public boolean addCourseToSchedule(String name, String section) {
		Course courseToAdd =  getCourseFromCatalog(name, section);
		boolean isInCato = false;
		for (int i = 0; i < catalog.size(); i++) {
			if(catalog.get(i).isDuplicate(courseToAdd)) {
				isInCato = true;
				
			}
		}
		if (!isInCato) {
			return false;
		}
		
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(courseToAdd)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		for (int i = 0; i < schedule.size(); i++) {
			try {
				schedule.get(i).checkConflict(courseToAdd);
			} catch (ConflictException e) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		schedule.add(courseToAdd);
		return true; 
	}
	
	/**
	 * Add an event to the schedule. If there is a duplicate, an IllegalArgumentException is thrown
	 * @param eventTitle The title of event
	 * @param eventMeetingDays The meeting days of event
	 * @param eventStartTime The start time of event
	 * @param eventEndTime The end time of event
	 * @param eventDetails The details of event
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event eventToAdd =  new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(eventToAdd)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
		}
		for (int i = 0; i < schedule.size(); i++) {
			try {
				schedule.get(i).checkConflict(eventToAdd);
			} catch (ConflictException e) {
					throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
		}
		schedule.add(eventToAdd);
		
	}

	/**
	 * Remove a course to a schedule
	 * @param idx TODO
	 * @return true if a course has been sucessfully removed, false otherwise
	 */
	public boolean removeActivityFromSchedule(int idx) {
		boolean isInSche = false;
		try {
			schedule.remove(schedule.get(idx));
			isInSche = true;
			
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		return isInSche;
	}

	/**
	 * Return a course from the catalog
	 * @param name name of the class
	 * @param section the class section
	 * @return a course from the catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if(catalog.get(i).getName().equals(name) && 
					catalog.get(i).getSection().equals(section)) {
					return catalog.get(i);
				}
			} 
		return null;
	}

	/**
	 * Return the title of the schedule
	 * @return the title
	 */
	public String getScheduleTitle(){
		return this.title;
		
	}
	
	/**
	 * Sets a title for the schedule
	 * @param title the title
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setScheduleTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
			
	}
	
	/**
	 * Export the schedule to a file
	 * @param fileName file to be exported to
	 */
	public void exportSchedule(String fileName) {
		try {
		ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved");
		}
	}

	/**
	 * Reset the schedule to blank
	 */
	public void resetSchedule() {
		this.schedule = new ArrayList<Activity>();
		
	}

	

}
