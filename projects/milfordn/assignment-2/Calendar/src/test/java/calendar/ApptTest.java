package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;

import static org.junit.Assert.*;
public class ApptTest {
	
	int startHour=21;
	 int startMinute=30;
	 int startDay=15;
	 int startMonth=01;
	 int startYear=2018;
	 String title="Birthday Party";
	 String description="This is my birthday party.";
	 //Construct a new Appointment object with the initial data	 
	 Appt appt = new Appt(startHour,
			  startMinute ,
			  startDay ,
			  startMonth ,
			  startYear ,
			  title,
			 description);
	
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {	 
	// assertions

		try{ assertTrue(appt.getValid()); } finally{}
		 assertEquals(21, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(15, appt.getStartDay());
		 assertEquals(01, appt.getStartMonth());
		 assertEquals(2018, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());         		
	 }

	 /**
	  * Test that get methods work for recurring properties
	  */
	 @Test
	  public void test02()  throws Throwable  {
		 assertFalse(appt.isRecurring());

		 appt.setRecurrence(null, Appt.RECUR_BY_YEARLY, 1, Appt.RECUR_NUMBER_FOREVER);
		 assertArrayEquals(new int[0], appt.getRecurDays());
		 assertEquals(Appt.RECUR_BY_YEARLY, appt.getRecurBy());
		 assertEquals(1, appt.getRecurIncrement());
		 assertEquals(Appt.RECUR_NUMBER_FOREVER, appt.getRecurNumber());
		 assertTrue(appt.isRecurring());
	 }
//add more unit tests as you neede

	 @Test
	 public void test03() throws Throwable {
		 Appt a3 = new Appt(1, 1, 2, 2, 2018, "A", "B");
		 try{ assertEquals("\t02/2/2018 at 1:1pm ,A, B.\n", a3.toString()); } finally{}
	 }

	 @Test
	 public void test04() throws Throwable {
		Appt a2 = new Appt(0, 0, 0, 3, 0, "", "");

		a2.setStartYear(2018);
		assertEquals(2018, a2.getStartYear());

		a2.setStartDay(27);
		assertEquals(27, a2.getStartDay());

		a2.setStartMonth(03);
		assertEquals(03, a2.getStartMonth());

		a2.setStartHour(12);
		assertEquals(12, a2.getStartHour());

		a2.setStartMinute(30);
		assertEquals(30, a2.getStartMinute());

		a2.setTitle("Tomorrow");
		assertEquals("Tomorrow", a2.getTitle());
		a2.setTitle(null);
		assertEquals("", a2.getTitle());

		a2.setDescription("Tomorrow morning");
		assertEquals("Tomorrow morning", a2.getDescription());
		a2.setDescription(null);
		assertEquals("", a2.getDescription());

		assertTrue(a2.getValid());
	 }
	
}
