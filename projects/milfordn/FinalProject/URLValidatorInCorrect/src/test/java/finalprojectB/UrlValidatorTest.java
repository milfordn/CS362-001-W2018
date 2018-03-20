
package finalprojectB;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Calendar;
import java.util.Random;

import javax.naming.spi.DirStateFactory.Result;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

public class UrlValidatorTest extends TestCase {

   public UrlValidatorTest(String testName) {
      super(testName);
   }   
   
   public void testManualTest01()
   {
        UrlValidator v1 = new UrlValidator();
        assertFalse(v1.isValid(null));       
        try{ assertTrue(v1.isValid("https://www.youtube.com/channel/UC6-ymYjG0SU0jUWnWh9ZzEQ")); } finally {}
        try{ assertTrue(v1.isValid("https://oregonstate.instructure.com/courses/1662159/assignments/7194286")); } finally {}
        try{ assertFalse(v1.isValid("http://broken..wrong\file.trb")); } finally {}
        try{ assertTrue(v1.isValid("http://www.google.com")); } finally{}
   }

   public void testManualTest02(){
        String[] schemes = null;// {"http"};
        UrlValidator v1 = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
        assertFalse(v1.isValid(null));       
        try{ assertTrue(v1.isValid("https://www.youtube.com/channel/UC6-ymYjG0SU0jUWnWh9ZzEQ")); } finally {}
        try{ assertTrue(v1.isValid("https://oregonstate.instructure.com/courses/1662159/assignments/7194286")); } finally {}
        try{ assertFalse(v1.isValid("http://broken..wrong\file.trb")); } finally {}
        try{ assertTrue(v1.isValid("http://www.google.com")); } finally{}
   }   

   public void testManualTest03(){
        UrlValidator v1 = UrlValidator.getInstance();
        assertFalse(v1.isValid(null));       
        try{ assertTrue(v1.isValid("https://www.youtube.com/channel/UC6-ymYjG0SU0jUWnWh9ZzEQ")); } finally {}
        try{ assertTrue(v1.isValid("https://oregonstate.instructure.com/courses/1662159/assignments/7194286")); } finally {}
        try{ assertFalse(v1.isValid("http://broken..wrong\file.trb")); } finally {}
        try{ assertTrue(v1.isValid("http://www.google.com")); } finally{}
    }   
   
    public void testSchemePartitionTest() {
        UrlValidator uv = new UrlValidator();
  
        try {assertTrue(uv.isValid("http://oregonstate.edu"));} finally {}
        try {assertTrue(uv.isValid("https://oregonstate.edu"));} finally {}
        try {assertTrue(uv.isValid("ftp://oregonstate.edu"));} finally {}
        try {assertFalse(uv.isValid("3ttp://oregonstate.edu"));} finally {}
        try {assertFalse(uv.isValid("ABC://oregonstate.edu"));} finally {}
        try {assertFalse(uv.isValid("http:/oregonstate.edu"));} finally {}
        try {assertFalse(uv.isValid("http:oregonstate.edu"));} finally {}
        try {assertFalse(uv.isValid("http//oregonstate.edu"));} finally {}
    }

    public void testAddressPartitionTest() {
        UrlValidator uv = new UrlValidator();
  
        try {assertTrue(uv.isValid("https://oregonstate.edu"));} finally {}
        try {assertTrue(uv.isValid("https://www.oregonstate.edu"));} finally {}
        try {assertTrue(uv.isValid("https://oregonstate.edu/file"));} finally {}
        try {assertTrue(uv.isValid("https://oregonstate.edu/"));} finally {}
        try {assertTrue(uv.isValid("https://www.oregonstate.edu/"));} finally {}
        try {assertTrue(uv.isValid("0.0.0.0"));} finally {}
        try {assertTrue(uv.isValid("255.255.255.255"));} finally {}
        try {assertTrue(uv.isValid("256.256.256.256"));} finally {}
    }

    public void testQueryPartitionTest() {
        UrlValidator uv = new UrlValidator();
  
        try {assertTrue(uv.isValid("https://github.com/loughrao/CS362-001-W2018/blob/master/projects/loughrao/assignment-1/Calendar/src/test/java/calendar/ApptTest.java?asdgbjud=11D"));} finally {}
        try {assertTrue(uv.isValid("https://youtube.com/watch?hc_ref=ARQuoD2MzwRKxqfQuN2XpkO3aiwKKba5mM_G"));} finally {}
    } 
   
   public void testIsValid()
   {
       
       long startTime = Calendar.getInstance().getTimeInMillis();
       long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
       UrlValidator v = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);

       System.out.println("Start Testing");

       while(elapsed < (10 * 1000)){
            Random r = new Random(System.currentTimeMillis());

            for(int i = 0; i < 10000; i++){
                ResultPair[] URL = URLGenerator.getURL(r);
                Boolean valid = true;
                String s = "";

                if(URL == null){
                    valid = false;
                    s = null;
                }
                else{
                    for(ResultPair p : URL){
                        if(p.valid) valid = false;
                        s += p.item;
                    }
                }

                Boolean res = v.isValid(s);
                try{ assertTrue(valid == res); } finally { System.out.println("Failed on " + s + "\t Expected " + valid + " Got " + res); };
            }
            elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);

            System.out.println("Elapsed Time: " + elapsed);
       }
   }
   


}
