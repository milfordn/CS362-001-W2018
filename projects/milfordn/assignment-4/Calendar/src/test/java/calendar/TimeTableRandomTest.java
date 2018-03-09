package calendar;

import java.util.*;
import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {

    private final static float SET_INVALID = 0.05f;
    static final int NUM_TESTS = 100;
	private static final long TestTimeout = 20 * 500 * 1; /* Timeout at 10 seconds */

    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"addAppt", "deleteAppt", "getApptRange"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
    }

    public Appt getRandomAppt(Random rand){
        int startHour=ValuesGenerator.RandInt(rand);
        int startMinute=ValuesGenerator.RandInt(rand);
        int startDay=ValuesGenerator.RandInt(rand);
        int startMonth=ValuesGenerator.getRandomIntBetween(rand, 1, 11);
        int startYear=ValuesGenerator.RandInt(rand);
        String title=ValuesGenerator.getString(rand);
        String description=ValuesGenerator.getString(rand);
        //Construct a new Appointment object with the initial data	 
        Appt appt = new Appt(startHour,
                startMinute ,
                startDay ,
                startMonth ,
                startYear ,
                title,
                description);


        int sizeArray=ValuesGenerator.getRandomIntBetween(rand, 0, 8);
        int[] recurDays=ValuesGenerator.generateRandomArray(rand, sizeArray);
        int recur=ApptRandomTest.RandomSelectRecur(rand);
        int recurIncrement = ValuesGenerator.RandInt(rand);
        int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(rand);
        appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);

        if(ValuesGenerator.getBoolean(SET_INVALID, rand)) appt.setStartHour(-1);

        return appt;
    }

    public GregorianCalendar getRandomDay(Random rand){
        int startDay=ValuesGenerator.RandInt(rand);
        int startMonth=ValuesGenerator.getRandomIntBetween(rand, 0, 11);
        int startYear=ValuesGenerator.RandInt(rand);
        
        return new GregorianCalendar(startYear, startMonth, startDay);
    }

    public int getRandomIndex(Random rand, LinkedList<Appt> L){
        return ValuesGenerator.getRandomIntBetween(rand, 0, L.size() - 1);
    }

    /**
     * Generate Random Tests that tests TimeTable Class.
     */
	 @Test
    public void radnomtest()  throws Throwable  {		 
		 		 
        long startTime = Calendar.getInstance().getTimeInMillis();
        long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		 
        System.out.println("Start testing...");
		 
        for (int iteration = 0; elapsed < TestTimeout; iteration++) {
            long randomseed =System.currentTimeMillis(); //10
            //System.out.println(" Seed:"+randomseed );
            Random random = new Random(randomseed);

            LinkedList<Appt> appts = new LinkedList<Appt>();

            TimeTable t = new TimeTable();

            for (int i = 0; i < NUM_TESTS; i++) {
                String methodName = RandomSelectMethod(random);
                if (methodName.equals("addAppt")){
                    appts.add(getRandomAppt(random));
                }
                else if(methodName.equals("deleteAppt") && appts.size() > 0){
                    boolean apptNull = ValuesGenerator.getBoolean(SET_INVALID, random);
                    boolean listNull = ValuesGenerator.getBoolean(SET_INVALID, random);
                    t.deleteAppt(listNull ? null : appts, apptNull ? null : appts.get(getRandomIndex(random, appts)));
                }
                else if(methodName.equals("getApptRange")){
                    try { t.getApptRange(appts, getRandomDay(random), getRandomDay(random)); } catch(DateOutOfRangeException d) {}
                }
            }
        
            elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
                if((iteration%10000)==0 && iteration!=0)
                        System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
            
            }
	 
		 System.out.println("Done testing...");		 
    }	
}
