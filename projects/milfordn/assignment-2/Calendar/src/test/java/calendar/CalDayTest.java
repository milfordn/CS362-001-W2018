package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;


import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

	GregorianCalendar g = new GregorianCalendar(2018, GregorianCalendar.APRIL, 26);
	CalDay c = new CalDay(g);

	@Test
	public void test01()  throws Throwable  {
		try{ assertEquals(2018, c.getYear()); } finally{}
		assertEquals(GregorianCalendar.APRIL, c.getMonth());
		assertEquals(26, c.getDay());
		assertTrue(c.isValid());
	}

	@Test
	public void test02()  throws Throwable  {
		Appt a = new Appt(17, 12, 26, 4, 2018, "Birthday Party", "Increment age by one");
		c.addAppt(a);
		try { assertEquals(1, c.getSizeAppts()); } finally{}

		Appt b = new Appt(11, 12, 26, 4, 2018, "Class", "Go to class in Weniger");
		c.addAppt(b);
		try { assertEquals(2, c.getSizeAppts()); } finally{}

		//They must be in the same order
		try { assertEquals(b, c.getAppts().get(0)); } finally{}
		try { assertEquals(a, c.getAppts().get(1)); } finally{}
		
		assertNotNull(c.iterator());
	}

	@Test
	public void test03() throws Throwable {
		CalDay c2 = new CalDay();
		assertFalse(c2.isValid());
		assertNull(c2.iterator());
		assertEquals("", c2.toString());
	}

	@Test
	public void test04() throws Throwable {
		try{ assertEquals("\t --- 03/26/2018 --- \n --- -------- Appointments -------- --- \n\n", c.toString()); } finally {}
	}
//add more unit tests as you needed	
}
