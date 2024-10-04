/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Event class
 * @author Louis Ton
 */
public class Event extends Activity {

	/** The event detaisl*/
	private String eventDetails;
	
	
	/**
	 * Create an Event with title, meetingDays, startTime, endTime, and eventDetails
	 * @param title the title
	 * @param meetingDays the meeting days
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param eventDetails the envent details
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
        super(title, meetingDays, startTime, endTime);
        setEventDetails(eventDetails);
    }
	
	
	
	/**
	 * Gets the eventDetails
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}



	/**
	 * Sets the event details. If the eventDetails is null, an
	 * IllegalArgumentException is thrown
	 * @param eventDetails The event details
	 * @throws IllegalArgumentException if eventDetails is null
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException ("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}	



	/**
	 * Return a String array with first two values should being empty since Event doesn’t have a 
	 * name or section, and the last two values should be the title and the meeting string.
	 * @return a String array of length four with the information
	 */
	public String[] getShortDisplayArray() {
		String [] shortDisplay = new String [4];
		shortDisplay[0] = "";
		shortDisplay[1] = "";
		shortDisplay[2] = getTitle();
		shortDisplay[3]	= getMeetingString();
		return shortDisplay;
	}

	/**
	 * Return a String array with first two values should being empty since Event doesn’t have a 
	 * name or section, The third value is the title followed by two values with empty strings.
	 * The last two are the meeting string and eventDetails.
	 * @return a String array of length seven with the information
	 */
	public String[] getLongDisplayArray() {
		String [] longDisplay = new String [7];
		longDisplay[0] = "";
		longDisplay[1] = "";
		longDisplay[2] = getTitle();
		longDisplay[3] = "";
		longDisplay[4] = "";
		longDisplay[5] = getMeetingString();
		longDisplay[6] = eventDetails;
		return longDisplay;
	}
	
	
	/**
	 * First, check if the parameter is an instance of Event and cast to check Event’s fields.
	 * Then, determine if they are duplicate. Two Event are considered duplicates if they have the same name.
	 * @param activity The event to check
	 * @return true if they are duplicate
	 */
	public boolean isDuplicate (Activity activity) {
		if (activity instanceof Event) {
			Event that = (Event) activity;
			return this.getTitle().equals(that.getTitle());
		}
		return false;
	}
	
	/**
	 * Set the meetingDays,startTime, and endTime for Event. If the meetingDays is null or empty,
	 * or any character that is not 'M', 'T', 'W', 'H', 'F', 'S', or 'U',
	 * an IllegalArgumentExcpetion is thrown
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		int monCount = 0;
		int tueCount = 0;
		int wedCount = 0;
		int thuCount = 0;
		int friCount = 0;
		int satCount = 0;
		int sunCount = 0;
		char [] meetingDaysChar = meetingDays.toCharArray();
		
		for (int i = 0; i < meetingDaysChar.length; i++) {
			char day = meetingDaysChar[i];
			if (day == 'M') {
				monCount++;
			} else if (day == 'T') {
				tueCount++;
			} else if (day == 'W') {
				wedCount++;
			} else if (day == 'H') {
				thuCount++;
			} else if (day == 'F') {
				friCount++;
			} else if (day == 'S') {
				satCount++; 
			} else if (day == 'U') {
				sunCount++;
			} else {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}	
		}
		if (monCount > 1 || tueCount > 1 || wedCount > 1 || thuCount > 1 || 
				friCount > 1 || satCount > 1 || sunCount > 1) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/**
	 * Create a String with all information
	 * @return a String with all information
	 */
	@Override
	public String toString() {
		return this.getTitle() + "," + this.getMeetingDays() + "," + this.getStartTime() + 
				"," + this.getEndTime() + "," + this.getEventDetails();
	}



	


	


	

	

	
	
	

}
