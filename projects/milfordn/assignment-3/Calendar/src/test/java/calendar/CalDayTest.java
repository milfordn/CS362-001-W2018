package calendar;
/**
 *  This class provides a1 basic set of test cases for the
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
		assertNotNull(c.getAppts());
		assertEquals(0, c.getSizeAppts());
	}

	@Test
	public void test02()  throws Throwable  {
		Appt a1 = new Appt(17, 12, 26, 4, 2018, "Birthday Party", "Increment age by one");
		assertTrue(a1.getValid());
		c.addAppt(a1);
		try { assertEquals(1, c.getSizeAppts()); } finally{}

		Appt a2 = new Appt(11, 12, 26, 4, 2018, "Class", "Go to class in Weniger");
		assertTrue(a2.getValid());
		c.addAppt(a2);
		try { assertEquals(2, c.getSizeAppts()); } finally{}

		Appt a3 = new Appt(11, 12, 26, 4, 2018, "Lunch", "food");
		c.addAppt(a3);
		assertEquals(3, c.getSizeAppts());

		//They must be in the same order
		try { assertEquals(a2, c.getAppts().get(0)); } finally{}
		try { assertEquals(a3, c.getAppts().get(1)); } finally{}
		assertEquals(a1, c.getAppts().get(2));
		
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
		try{ assertEquals("\t --- 3/26/2018 --- \n --- -------- Appointments ------------ --- \n\n", c.toString()); } finally {}
	}

	@Test
	public void test05() throws Throwable {
		CalDay c2 = new CalDay(g);
		Appt a1 = new Appt(5, 3, 1, 4, 2018, "A", "B");
		c2.addAppt(a1);

		assertEquals("\t --- 3/26/2018 --- \n --- -------- Appointments ------------ --- \n" + a1.toString() + " \n", c2.toString());
	}
//add more unit tests as you needed	
}
