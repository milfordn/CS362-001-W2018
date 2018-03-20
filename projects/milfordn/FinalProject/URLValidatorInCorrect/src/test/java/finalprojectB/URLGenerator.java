package finalprojectB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sun.org.apache.regexp.internal.RE;
import junit.framework.TestCase;
import org.junit.Test;

public class URLGenerator {
    private final static int MAX_VALUE=256;
    private final static float SET_TO_NULL = 0.05f;
    public static int RandInt(Random random){
        int n = random.nextInt(MAX_VALUE);// get a random number between 0 (inclusive) and  MAX_VALUE=10 (exclusive)
        return (int) n;
    }

    public static boolean getBoolean(float probability, Random random){
        return random.nextFloat() < probability;
    }


    private static final ResultPair[] SCHEMES = {
            new ResultPair("http://",true),
            new ResultPair("https://",true),
            new ResultPair("ftp://",true),
            new ResultPair("afp://",true),
            new ResultPair("smb://",true),

            new ResultPair("://",false),
            new ResultPair("ftp:",false),
            new ResultPair("123://",false),
            new ResultPair("9https://",false),
            new ResultPair("3abc://",false),
            new ResultPair("http",false),
            new ResultPair("https//",false),
            new ResultPair("ftp/",false),
            new ResultPair("http:/",false)
    };
    private static final ResultPair[] ADDRESS = {
            new ResultPair("google.com",true),
            new ResultPair("www.google.com",true),
            new ResultPair("www.github.com",true),
            new ResultPair("comcast.net",true),
            new ResultPair("1.1.1.1",true),
            new ResultPair("255.255.255.255",true),

            new ResultPair(".edu",false),
            new ResultPair(".com",false),
            new ResultPair("256.256.256.256",false),
            new ResultPair("255.255.255.255.255",false),
            new ResultPair("1.",false),
            new ResultPair("netflix.",false),
            new ResultPair("netflix.i",false),
            new ResultPair("oregonstate.eduk",false),
            new ResultPair("www.1.1.1.1.com",false),
            new ResultPair("website",false),
            new ResultPair("",false)
    };
    private static final ResultPair[] PORT = {
            new ResultPair(":7777",true),
            new ResultPair(":100", true),
            new ResultPair("",true),

            new ResultPair(":",false),
            new ResultPair(":-1",false),
            new ResultPair("a",false),
            new ResultPair("7777",false),
            new ResultPair(":abc",false),
            new ResultPair("-1",false),
            new ResultPair(":65636",false)
    };
    private static final ResultPair[] FILE = {
            new ResultPair("/folder", true),
            new ResultPair("/SchoolWork1", true),
            new ResultPair("/1SchoolWork", true),
            new ResultPair("/Ab#!cD", true),
            new ResultPair("/folder/SchoolWork", true),
            new ResultPair("", true),

            new ResultPair("/..", false),
            new ResultPair("/../folder", false),
            new ResultPair("/..//document", false),
            new ResultPair("/Folder//school", false)
    };
    private static final ResultPair[] QUERY = {
            new ResultPair("?abcdefg",true),
            new ResultPair("?12##!cca",true),
            new ResultPair("?this=that", true),
            new ResultPair("",true),

            new ResultPair("abc",false),
            new ResultPair("12=12",false),
            new ResultPair("s=google",false),
    };
    public static ResultPair getSCHEME(Random random) {
        return SCHEMES[random.nextInt(SCHEMES.length)];
    }
    public static ResultPair getADDRESS(Random random) {
        return ADDRESS[random.nextInt(ADDRESS.length)];
    }
    public static ResultPair getPORT(Random random) {
        return PORT[random.nextInt(PORT.length)];
    }
    public static ResultPair getFILE(Random random) {
        return FILE[random.nextInt(FILE.length)];
    }
    public static ResultPair getQUERY(Random random) {
        return QUERY[random.nextInt(QUERY.length)];
    }

    public static ResultPair[] getURL(Random random) {
        if (URLGenerator.getBoolean(SET_TO_NULL,random)) return null;
        ResultPair[] URL = new ResultPair[5];
        int randNum;
        for(int i=0; i<5; i++) {
            if(URLGenerator.getBoolean(.75f,random)){
                randNum = random.nextInt(10);
            }
            else if(URLGenerator.getBoolean(.75f,random)){
                randNum = random.nextInt(50);
            }
            else {
                randNum = random.nextInt(100);
            }
            if(i==0){
                URL[i] = getSCHEME(random);
            }
            else if(i==1){
                URL[i] = getADDRESS(random);
            }
            else if(i==2){
                URL[i] = getPORT(random);
            }
            else if(i==3){
                URL[i] = getFILE(random);
            }
            else{
                URL[i] = getQUERY(random);
            }
        }
        return URL;
    }
}
