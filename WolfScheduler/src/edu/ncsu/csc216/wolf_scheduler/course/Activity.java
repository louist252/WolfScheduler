package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Activity class
 * @author Louis Ton
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** Upper bound of hours in military time */
	private static final int UPPER_HOUR = 23;
	/** Upper bound of minute in military time */
	private static final int UPPER_MINUTE = 59;
		
	/**
	 * Creat an Activity with title, meetingDays, startTime, and endTime
	 * @param title The title
	 * @param meetingDays The meeting
	 * @param startTime The start time 
	 * @param endTime The end time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Returns the Course's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title. If the title is null or empty,
	 * IllegalArgumentException is thrown
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is invalid
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0) {
	        throw new IllegalArgumentException("Invalid title.");
	
		}
		this.title = title;
	}

	/**
	 * Sets the meeting days and time for the Activity. If the startTime and endTime are
	 * not between 0000 and 2359 or an invalid military time, end time is less than the start time,
	 * or meetingDays is null or empty, a IllegalArgumentException is thrown
	 * @param meetingDays Course's meeting days
	 * @param startTime Course's start time
	 * @param endTime Course's end time
	 * @throws IllegalArgumentException if meetingDays, startTime, or endTime is invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		//Throw exception if meetingDays in null or empty
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
			int startHour = startTime / 100;
			int startMin = startTime % 100;
			int endHour = endTime / 100;
			int endMin = endTime % 100;
			
			if (startHour < 0 || startHour > UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			} else if (startMin < 0 || startMin > UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			} else if (endHour < 0 || endHour > UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			} else if (endMin < 0 || endMin > UPPER_MINUTE) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			} else if (endHour < startHour) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
		
	}

	/**
	 * Returns the Course's meeting days
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Returns the meeting days and time
	 * @return A meetind day and time string
	 */
	public String getMeetingString() {
		if ("A".equals(meetingDays)) { 
		return "Arranged";
		} else  {
			String startTimeString = getTimeString (startTime);
			String endTimeString = getTimeString (endTime);
			return meetingDays + " " + startTimeString + "-" + endTimeString;
		}
	}

	/**
	 * Convert military time to standard time string
	 * @param time military time
	 * @return A string of stadard time
	 */
	private String getTimeString(int time) {
		int hour = time / 100;
		int min = time % 100;
		String hourString = "";
		String minString = "";
		String timeString = ""; 
	
		//Check to see if minute is less that 10 to concatenate a 0
		if (min < 10) {
			minString = "0" + String.valueOf(min);
		} else {
			minString = String.valueOf(min);
		}
		
		//Check hour to see if it has passed noon or not
		if(hour <= 12) {
			hourString = String.valueOf(hour);
		} else if (hour > 12) {
			hourString = String.valueOf(hour - 12);
		}
		
		
		//Build the string
		if (hour < 12) {
			timeString = hourString + ":" + minString + "AM";
		} else if (hour >= 12) {
			timeString = hourString + ":" + minString + "PM";
		}
		
		return timeString;
		
	}
	
	/**
	 * The abstract methods that will provide a short  version of the array of information to provide to the GUI.
	 * @return a short version of the array of information
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * The abstract methods that will provide a long version of the array of information to provide to the GUI.
	 * @return a long version of the array of information
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * The abstract method to see if the Course to be added is a duplicate of a Course already in the schedule
	 * @param activity The activity
	 * @return true if duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Generate a hashCode for Activity using all fields
	 * @return hashCode for Activity
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	
	/**
	 * Compares a given object to this object for equality on all fields
	 * @param obj the Object to compare
	 * @return true if the objects are the same on all fields
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Check the time conflict. A conflict occurs when the ending time of an event is the same
	 * as the starting time of another event, or an event takes place in the same time as another event.
	 * If a conflict happen, an ConflictException is thrown
	 * @param possibleConflictingActivity the Activity to check to see if there is any conflict
	 * @throws ConflictException when there is a time conflict
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		//Start and end time of this Activity
		int thisStartHour = this.startTime / 100;
		int thisStartMin = this.startTime % 100;
		int thisEndHour = this.endTime / 100;
		int thisEndMin = this.endTime % 100;
		
		//Start and end time of possibleConflictActivity 
		int thatStartHour = possibleConflictingActivity.startTime / 100;
		int thatStartMin = possibleConflictingActivity.startTime % 100;
		int thatEndHour = possibleConflictingActivity.endTime / 100;
		int thatEndMin = possibleConflictingActivity.endTime % 100;
		
		//Check to see if there is there is a shared day in the week
		boolean shareDay = false;
		if (this.meetingDays.contains("M") && possibleConflictingActivity.meetingDays.contains("M")) {
			shareDay = true;
		} else if (this.meetingDays.contains("T") && possibleConflictingActivity.meetingDays.contains("T")) {
			shareDay = true;
		} else if (this.meetingDays.contains("W") && possibleConflictingActivity.meetingDays.contains("W")) {
			shareDay = true;
		} else if (this.meetingDays.contains("H") && possibleConflictingActivity.meetingDays.contains("H")) {
			shareDay = true;
		} else if (this.meetingDays.contains("F") && possibleConflictingActivity.meetingDays.contains("F")) {
			shareDay = true;
		} else if (this.meetingDays.contains("S") && possibleConflictingActivity.meetingDays.contains("S")) {
			shareDay = true;
		} else if (this.meetingDays.contains("U") && possibleConflictingActivity.meetingDays.contains("U")) {
			shareDay = true;
		}
		
		if(shareDay){
			if (thisStartHour == thatEndHour && thisStartMin == thatEndMin) {
				throw new ConflictException();
			} else if (thisEndHour == thatStartHour && thisEndMin == thatStartMin) {
				throw new ConflictException();
			} else if (thisStartHour == thatStartHour && thisStartMin == thatStartMin) {
				throw new ConflictException();
			} else if (thisStartHour  >= thatStartHour && thisEndHour <= thatEndHour || 
					thatStartHour  >= thisStartHour && thatEndHour <= thisEndHour) {		
				throw new ConflictException();
			} 
		}
	}
}