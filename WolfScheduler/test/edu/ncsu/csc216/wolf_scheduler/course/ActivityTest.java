/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests for Activity class
 * @author Louis Ton
 */
class ActivityTest {

	/**
	 * Test method for checkConflict in Activity with no conflict
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
	    
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	
	}
	
	/**
	 * Test method for checkConflict in Activity with conflict
	 */
	@Test
	public void testCheckConflictWithConflict() {
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	    
	    
	    Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "T", 1330, 1445);
	    Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "T", 1445, 1500);
	    
	    Exception e3 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
	    assertEquals("Schedule conflict.", e3.getMessage());
	    
	    Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
	    assertEquals("Schedule conflict.", e4.getMessage());
	    
	    Activity a5 = new Event("Lunch", "S", 1200, 1300, "Lunch with friends");
	    Activity a6 = new Event("Workout", "S", 1300, 1400, "Cardio");
	    
	    Exception e5 = assertThrows(ConflictException.class, () -> a5.checkConflict(a6));
	    assertEquals("Schedule conflict.", e5.getMessage());
	    
	    Exception e6 = assertThrows(ConflictException.class, () -> a6.checkConflict(a5));
	    assertEquals("Schedule conflict.", e6.getMessage());
	    
	    Activity a7 = new Event("Lunch", "U", 1200, 1300, "Lunch with friends");
	    Activity a8 = new Event("Lunch", "U", 1300, 1400, "");
	    
	    Exception e7 = assertThrows(ConflictException.class, () -> a7.checkConflict(a8));
	    assertEquals("Schedule conflict.", e7.getMessage());
	    
	    Exception e8 = assertThrows(ConflictException.class, () -> a8.checkConflict(a7));
	    assertEquals("Schedule conflict.", e8.getMessage());
	    
	    Activity a9 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "WHF", 1330, 1445);
	    Activity a10 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "HF", 1445, 1500);
	    
	    Exception e9 = assertThrows(ConflictException.class, () -> a9.checkConflict(a10));
	    assertEquals("Schedule conflict.", e9.getMessage());
	    
	    Exception e10 = assertThrows(ConflictException.class, () -> a10.checkConflict(a9));
	    assertEquals("Schedule conflict.", e10.getMessage());
	    
	    Activity a11 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1200, 1500);
	    Activity a12 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "WF", 1300, 1400);
	    
	    Exception e11 = assertThrows(ConflictException.class, () -> a11.checkConflict(a12));
	    assertEquals("Schedule conflict.", e11.getMessage());
	    
	    Exception e12 = assertThrows(ConflictException.class, () -> a12.checkConflict(a11));
	    assertEquals("Schedule conflict.", e12.getMessage());
	    
	    Activity a13 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "F", 1310, 1400);
	    Activity a14 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "F", 1320, 1500);
	    
	    Exception e13 = assertThrows(ConflictException.class, () -> a13.checkConflict(a14));
	    assertEquals("Schedule conflict.", e13.getMessage());
	    
	    Exception e14 = assertThrows(ConflictException.class, () -> a14.checkConflict(a13));
	    assertEquals("Schedule conflict.", e14.getMessage());
	    
	    
	    Activity a15 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1300, 1400);
	    Activity a16 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1300, 1500);
	    
	    Exception e15 = assertThrows(ConflictException.class, () -> a15.checkConflict(a16));
	    assertEquals("Schedule conflict.", e15.getMessage());
	    
	    Exception e16 = assertThrows(ConflictException.class, () -> a16.checkConflict(a15));
	    assertEquals("Schedule conflict.", e16.getMessage());
	}

}
