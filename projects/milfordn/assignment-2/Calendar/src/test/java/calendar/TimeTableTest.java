package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

	Appt a1 = new Appt(12, 1, 2, 04, 2018, "April 2nd", "lel");
	Appt a2 = new Appt(8, 1, 2, 4, 2018, "Start of Spring term", "get to work");
	Appt a3 = new Appt(4, 1, 20, 3, 2018, "Last Final", "Probably Algorithms");
	Appt a4 = new Appt(5, 1, 26, 4, 2018, "Birthday", "increment age");



	TimeTable t = new TimeTable();

	@Test
	public void test01()  throws Throwable  {			
			LinkedList<Appt> l = new LinkedList<Appt>();
			l.add(a1);
			l.add(a2);
			l.add(a3);
			l.add(a4);

			GregorianCalendar start = new GregorianCalendar(2018, GregorianCalendar.MARCH, 31);
			GregorianCalendar end = new GregorianCalendar(2018, GregorianCalendar.APRIL, 30);

			LinkedList<CalDay> l2 = t.getApptRange(l, start, end);

			try { assertEquals(30, l2.size()); } finally{}

			for(CalDay c : l2){
				assertTrue(c.isValid());
				GregorianCalendar g = new GregorianCalendar(c.getYear(), c.getMonth(), c.getDay());
				try { assertTrue(g.before(end) && g.after(start)); } finally{}
			}
	}
	
	@Test
	public void test02()  throws Throwable  {
		LinkedList<Appt> l = new LinkedList<Appt>();
		l.add(a1);
		l.add(a2);
		l.add(a3);
		l.add(a4);

		LinkedList<Appt> l2 = t.deleteAppt(l, a2);
		assertNotNull(l2);
		assertEquals(3, l.size());
		assertEquals(3, l2.size());

		l2 = t.deleteAppt(l, a1);
		try{
			assertNotNull(l2);
			assertEquals(2, l.size());
			assertEquals(2, l2.size());
		} finally {}
		
		LinkedList<Appt> l3 = t.deleteAppt(l, null);
		assertNull(l3);

		l3 = t.deleteAppt(null, a2);
		assertNull(l3);
	}

	@Test
	public void test03() throws Throwable {
		LinkedList<Appt> l = new LinkedList<Appt>();
		l.add(a1);
		l.add(a2);
		l.add(a3);
		l.add(a4);

		int[] is = {2, 0, 1, 3};

		LinkedList<Appt> l2 = t.permute(l, is);

		for(int i = 0; i < 4; i++){
			assertEquals(l.get(i), l2.get(is[i]));
		}
	}

	@Test(expected = DateOutOfRangeException.class)
	public void test04() throws Throwable {
		t.getApptRange(null, new GregorianCalendar(2018, GregorianCalendar.JANUARY, 29), new GregorianCalendar(2018, GregorianCalendar.JANUARY, 28));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test05() throws Throwable {
		LinkedList<Appt> l = new LinkedList<Appt>();
		l.add(a1);

		int [] is = {1, 2, 3};

		t.permute(l, is);
	}
//add more unit tests as you needed
}
