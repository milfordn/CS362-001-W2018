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

	Appt a1 = new Appt(1, 1, 1, GregorianCalendar.APRIL, 2018, "new month", "lel");
	Appt a2 = new Appt(8, 1, 2, GregorianCalendar.APRIL, 2018, "Start of Spring term", "get to work");
	Appt a3 = new Appt(4, 1, 20, GregorianCalendar.MARCH, 2018, "Last Final", "Probably Algorithms");
	Appt a4 = new Appt(5, 1, 26, GregorianCalendar.APRIL, 2018, "Birthday", "increment age");
	Appt a5 = new Appt(3, 1, 30, GregorianCalendar.APRIL, 2018, "End of month", "RIP April");

	TimeTable t = new TimeTable();

	@Test
	public void test01()  throws Throwable  {			
			LinkedList<Appt> l = new LinkedList<Appt>();
			l.add(a1);
			l.add(a2);
			l.add(a3);
			l.add(a4);
			l.add(a5);

			GregorianCalendar start = new GregorianCalendar(2018, GregorianCalendar.MARCH, 31);
			GregorianCalendar end = new GregorianCalendar(2018, GregorianCalendar.APRIL, 30);

			LinkedList<CalDay> l2 = t.getApptRange(l, start, end);

			try { assertEquals(30, l2.size()); } finally{}

			LinkedList<CalDay> collected = new LinkedList<CalDay>();
			int i = 0;
			for(CalDay c : l2){
				assertTrue(c.isValid());
				GregorianCalendar g = new GregorianCalendar(c.getYear(), c.getMonth(), c.getDay());
				try { assertTrue(!g.before(start) && !g.after(end)); } finally { }
				i += c.getSizeAppts();
				if(c.getSizeAppts() > 0) collected.add(c);
			}
			assertEquals(3, i);

			CalDay firstDay = collected.get(0);
			assertEquals(a1, firstDay.getAppts().get(0));
			assertEquals(1, firstDay.getDay());
			assertEquals(GregorianCalendar.APRIL, firstDay.getMonth());
	}
	
	@Test
	public void test02()  throws Throwable  {
		LinkedList<Appt> l = new LinkedList<Appt>();
		l.add(a1);
		l.add(a2);
		l.add(a3);
		l.add(a4);

		LinkedList<Appt> l2 = t.deleteAppt(l, a3);
		assertNotNull(l2);
		assertEquals(3, l.size());
		assertEquals(3, l2.size());
		assertEquals(a2, l2.get(1));

		l2 = t.deleteAppt(l, a2);
		try{
			assertNotNull(l2);
			assertEquals(2, l.size());
			assertEquals(2, l2.size());
			assertEquals(a4, l2.get(1));
		} finally {}
		
		assertNull(t.deleteAppt(l, a1));
		assertNull(t.deleteAppt(l, a4));

		LinkedList<Appt> l3 = t.deleteAppt(l, null);
		assertNull(l3);

		l3 = t.deleteAppt(null, a2);
		assertNull(l3);

		assertNull(t.deleteAppt(l2, a2));

		Appt aInv = new Appt(-1, 61, 0, 5, 5, "Invalid", "Invalid");
		assertFalse(aInv.getValid());
		assertNull(t.deleteAppt(l, aInv));
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
			//assertEquals(l.get(i), l2.get(is[i]));
			assertEquals(l.get(i), l2.get(i));
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

	@Test
	public void test06() throws Throwable {
		Appt recur = new Appt(13, 40, 2, 1, 2018, "CS325", "Algorithms");
		int[] recurDays = {Calendar.MONDAY, Calendar.WEDNESDAY, Calendar.FRIDAY};
		recur.setRecurrence(recurDays, Appt.RECUR_BY_WEEKLY, 1, 9);
		assert(recur.isRecurring());

		LinkedList<Appt> classes = new LinkedList<Appt>();
		classes.add(recur);

		LinkedList<CalDay> feb = t.getApptRange(classes, new GregorianCalendar(2018, GregorianCalendar.FEBRUARY, 4), new GregorianCalendar(2018, GregorianCalendar.FEBRUARY, 28));
		assertEquals(24, feb.size());
		
		int i = 0;
		for(CalDay c : feb){
			i += c.getSizeAppts();
		}
		assertEquals(9, i);
	}  

	@Test
	public void test07() throws Throwable {
		Appt recurY = a4;
		Appt recurM = a1;
		Appt recurW = new Appt(12, 5, 22, 2, 2018, "ENGR Recitation", "Statics");

		recurY.setRecurrence(null, Appt.RECUR_BY_YEARLY, 1, Appt.RECUR_NUMBER_FOREVER);
		recurM.setRecurrence(null, Appt.RECUR_BY_MONTHLY, 1, Appt.RECUR_NUMBER_FOREVER);
		recurW.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);

		LinkedList<Appt> recurList = new LinkedList<Appt>();
		recurList.add(recurY);
		recurList.add(recurM);
		recurList.add(recurW);

		LinkedList<CalDay> nextRecur = t.getApptRange(recurList, new GregorianCalendar(2018, GregorianCalendar.APRIL, 25), new GregorianCalendar(2018, GregorianCalendar.MAY, 2));

		LinkedList<Appt> collected = new LinkedList<Appt>();
		for(CalDay c : nextRecur){
			if(c.getSizeAppts() > 0)
				for(Appt a : c.getAppts()){
					collected.add(a);
				}
		}

		assertEquals(3, collected.size());
	}

	@Test
	public void test08() throws Throwable {
		Appt recurFail = new Appt(12, 30, 14, 1, 2018, "Robot Meeting", "woot");
		int[] days = {-1};
		recurFail.setRecurrence(days, Appt.RECUR_BY_WEEKLY, 1, 5);

		LinkedList<Appt> appts = new LinkedList<Appt>();
		appts.add(recurFail);

		LinkedList<CalDay> cal = t.getApptRange(appts, new GregorianCalendar(2018, GregorianCalendar.FEBRUARY, 20), new GregorianCalendar(2018, GregorianCalendar.FEBRUARY, 22));

		for(CalDay c : cal){
			assertEquals(0, c.getSizeAppts());
		}
	}
//add more unit tests as you needed
}
