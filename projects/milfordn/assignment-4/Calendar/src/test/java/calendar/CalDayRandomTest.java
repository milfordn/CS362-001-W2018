package calendar;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import org.junit.Test;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
    private final static float SET_INVALID = 0.05f;
    static final int NUM_TESTS = 100;
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */

    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"addAppt"};// The list of the of methods to be tested in the Appt class

    	int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
    	            
        return methodArray[n] ; // return the method name 
    }

    public Appt getRandomAppt(Random rand){
        int startHour=ValuesGenerator.RandInt(rand);
        int startMinute=ValuesGenerator.RandInt(rand);
        int startDay=ValuesGenerator.RandInt(rand);;
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

        if(ValuesGenerator.getBoolean(SET_INVALID, rand)) appt.setStartHour(-1);

        return appt;
    }
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	  public void radnomtest()  throws Throwable  {
		 
        long startTime = Calendar.getInstance().getTimeInMillis();
        long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
		 
        System.out.println("Start testing...");
		 
		try{ 
			for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				long randomseed =System.currentTimeMillis(); //10
				//System.out.println(" Seed:"+randomseed );
				Random random = new Random(randomseed);

                GregorianCalendar g = new GregorianCalendar(
                    ValuesGenerator.RandInt(random),
                    ValuesGenerator.getRandomIntBetween(random, 0, 11),
                    ValuesGenerator.getRandomIntBetween(random, 0, 20)
                );
                CalDay calDay = new CalDay(g);

                for (int i = 0; i < NUM_TESTS; i++) {
					String methodName = RandomSelectMethod(random);
					if (methodName.equals("addAppt")){
                        calDay.addAppt(getRandomAppt(random));
                    }
                    else {
                        throw new Exception();
                    }
				}
		 
                elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			        if((iteration%10000)==0 && iteration!=0)
			              System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
			 
			    }
		    }catch(NullPointerException e){ }
	 
		 System.out.println("Done testing...");
	 }


	
}
