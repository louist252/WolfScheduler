package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * The Course class
 * @author Louis Ton
 */

public class Course extends Activity {
	

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Minimum length of the Course's name */
	private final static int MIN_NAME_LENGTH = 5;
	/** Maximum length of the Course's name */
	private final static int MAX_NAME_LENGTH = 8;
	/** Least amount of letter character in Course's name */
	private final static int MIN_LETTER_COUNT = 1;
	/** Most amount of letter character in Course's name */
	private final static int MAX_LETTER_COUNT = 4;
	/** Amount of digits in Course's name */
	private final static int DIGIT_COUNT = 3;
	/** Length of the section string */
	private final static int SECTION_LENGTH = 3;
	/** Minimum number of credit hours */
	private final static int MIN_CREDIT = 1;
	/** Maximum number of credit hours */
	private final static int MAX_CREDIT = 5;
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
	        int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	}
	
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays
	 * for courses that are arranged.
	 * @param name name of the Course
	 * @param title title of the Course
	 * @param section section of the Course
	 * @param credits credit hours for the Course
	 * @param instructorId instructor's unity ID
	 * @param meetingDays meeting days for the Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
		
	}

	/**
	 * Returns the Course's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		//Throw exception if the name is null
		if (name == null) {
	        throw new IllegalArgumentException("Invalid course name.");
		}
		
		//Throw exception if the name is an empty string
	    //Throw exception if the name contains less than 5 character or greater than 8 characters
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
	        throw new IllegalArgumentException("Invalid course name.");
		}
		
		char [] nameChar = name.toCharArray();
		int letCounter = 0;
		int numCounter = 0;
		boolean space = false;
		
		for (int i = 0; i < nameChar.length; i++) {
			if (!space) {
				if (Character.isLetter(nameChar[i])) {
					letCounter++;
				} else if (nameChar[i] == ' ') {
					space = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if (space) {
				if (Character.isDigit(nameChar[i])) {
					numCounter++;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			}
		}
		
	    //Check that the number of letters is correct
		if (letCounter < MIN_LETTER_COUNT || letCounter > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
	    //Check that the number of digits is correct
		if (numCounter != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		this.name = name;		
	}
	
	/**
	 * Returns the Course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the Course's section. If section is null, does not have three 
	 * characters, or any of the character is not a digit,
	 * IllegalArgumentException is thrown
	 * @param section the section to set
	 * @throws IllegalArgumentException if the section paramater is invalid
	 */
	public void setSection(String section) {
		//Throws exception if section is null or does not have three characters
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section."); 
		}
		
		//Check each digit
		char [] sectionChar = section.toCharArray();
		boolean allDigit = true;
		for (int i = 0; i < sectionChar.length; i++) {
			if(!Character.isDigit(sectionChar[i])) {
				allDigit = false;
			}
		}
		
		//Throws exception if not all character is digit
		if (!allDigit) {
			throw new IllegalArgumentException("Invalid section.");
		}
		this.section = section;
		
	}
	
	/**
	 * Returns the Course's credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course's credits. If credit hours are less than 1 or 
	 * greater than 5, IllegalArgumentException is thrown
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits is out of bound
	 */
	public void setCredits(int credits) {
		//Thorws exception if credits is out of bound
		if (credits < MIN_CREDIT || credits > MAX_CREDIT) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		
		this.credits = credits;
	}
	
	/**
	 * Returns the Course's instructor's unity ID
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the Course's instructor's unity ID. If instructorId is null or 
	 * empty string, IllegalArgumentException is thrown
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructorId parameter is invalid
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");

		}
		this.instructorId = instructorId;
	}
	
	
	/**
	 * Generate a hashCode for Course using all fields
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + 
	    		getStartTime() + "," + getEndTime(); 
	}

	/**
	 * Gets a short array of information based on the Course name, 
	 * section, title, and meeting string.
	 * @return an array of length 4 with the information
	 */
	public String[] getShortDisplayArray() {
		String[] shortDisplayArray = new String[4];
        shortDisplayArray[0] = getName();
        shortDisplayArray[1] = getSection();
        shortDisplayArray[2] = getTitle();
        shortDisplayArray[3] = getMeetingString();
        return shortDisplayArray;
	}

	/**
	 * Gets a long array of information Course name, section, title, credits, instructorId,
	 * meeting string, empty string (for a field that Event will have that Course does not).
	 * @return n array of length 7 with the information
	 */
	public String[] getLongDisplayArray() {
		String[] longDisplayArray = new String[7];
        longDisplayArray[0] = getName();
        longDisplayArray[1] = getSection();
        longDisplayArray[2] = getTitle();
        longDisplayArray[3] = String.valueOf(getCredits());
        longDisplayArray[4] = String.valueOf(getInstructorId());
        longDisplayArray[5] = getMeetingString();
        longDisplayArray[6] = "";
        return longDisplayArray;	
	}
	
	
	/**
	 * First, check if the parameter is an instance of Course and cast to check Course’s fields.
	 * Then, determine if they are duplicate. Two Courses are considered duplicates if they have the same name.
	 * @param activity The activity to check
	 * @return true if they are duplicate
	 */
	public boolean isDuplicate (Activity activity) {
		if (activity instanceof Course) {
			Course that = (Course) activity;
			return this.name.equals(that.name);
		}
		return false;
	}

	/**
	 * Set the meeting days and time. If the meetingDays is 'A', it must be the only character, and 
	 * start time and/or end time is listed when meeting days is ‘A’. If the meetingDays is not 'A',
	 * then the meetingDays can only consist of ‘M’, ‘T’, ‘W’, ‘H’, ‘F’. If those things are not true,
	 * an IllegalArgumentException is thrown
	 *
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if ("A".equals(meetingDays)) {
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
		} else {
			int monCount = 0;
			int tueCount = 0;
			int wedCount = 0;
			int thuCount = 0;
			int friCount = 0;
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
				} else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}	
			}
			if (monCount > 1 || tueCount > 1 || wedCount > 1 ||
					thuCount > 1 || friCount > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	
	
	
	
	
}
