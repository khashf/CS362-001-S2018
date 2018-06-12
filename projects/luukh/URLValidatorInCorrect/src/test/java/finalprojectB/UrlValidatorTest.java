
package finalprojectB;

import junit.framework.TestCase;

// Citation:
// This test suite is heavily inspired and inherited from 
// the programming-based test suit that we were introduced in
// the final project part A. Though we borrowed a bunch of (amazing) ideas
// from the Apache implementation, we have modified, restructured, reformated,
// and partition the test data by our own ideas. 
public class UrlValidatorTest extends TestCase {

    ResultPair[] testUrlScheme = { 
        new ResultPair("http://", true),
        new ResultPair("ftp://", true),
        new ResultPair("hkt://", true),
        new ResultPair("999://", false),
        new ResultPair("http:/", false),
        new ResultPair("http:", false),
        new ResultPair("http/", false),
        new ResultPair("://", false),
        new ResultPair("", true) 
    };

    ResultPair[] testUrlAuthority = {
        new ResultPair("www.google.com", true),
        new ResultPair("oregonstate.edu", true),
        new ResultPair("microsoft.com", true), 
        new ResultPair("0.0.0.0", true), 
        new ResultPair("255.255.255.255", true),
        new ResultPair("256.256.256.256", false), 
        new ResultPair("255.com", true),
        new ResultPair("1.2.3.4.5", false), 
        new ResultPair("1.2.3.4.", false), 
        new ResultPair("1.2.3", false),
        new ResultPair(".1.2.3.4", false), 
        new ResultPair("tinhte.v", false), 
        new ResultPair("oregonstate.com", true),
        new ResultPair("oregonstate.gov", true), 
        new ResultPair("kkk.", false), 
        new ResultPair(".kkk", false),
        new ResultPair("kkk", false), 
        new ResultPair("", false) 
    };

    ResultPair[] testUrlPort = { 
        new ResultPair(":80", true), 
        new ResultPair(":65535", true),
        new ResultPair(":0", true), 
        new ResultPair("", true), 
        new ResultPair(":-1", false),
        new ResultPair(":65636", false), 
        new ResultPair(":69k", false),
        new ResultPair(":k", false) 
    };

    ResultPair[] testPath = { 
        new ResultPair("/test1", true), 
        new ResultPair("/t123", true),
        new ResultPair("/$23", true), 
        new ResultPair("/..", false), 
        new ResultPair("/../", false),
        new ResultPair("/test1/", true), 
        new ResultPair("", true), 
        new ResultPair("/test1/file", true),
        new ResultPair("/..//file", false), 
        new ResultPair("/test1//file", false) 
    };
    
    // Test allow2slash, noFragment
    ResultPair[] testUrlPathOptions = { 
        new ResultPair("/test1", true), 
        new ResultPair("/khuongishot", true),
        new ResultPair("/$99999", true), 
        new ResultPair("/..", false), 
        new ResultPair("/../", false),
        new ResultPair("/../../", false),
        new ResultPair("/test9/", true), 
        new ResultPair("/#", false), 
        new ResultPair("", true),
        new ResultPair("/test9/file", true), 
        new ResultPair("/k999999/file", true), 
        new ResultPair("/#6969/file", true),
        new ResultPair("/../file", false), 
        new ResultPair("/..//file", false), 
        new ResultPair("/test9//file", true), // true with allow2slash
        new ResultPair("/#/file", false) 
    };

    ResultPair[] testUrlQuery = { 
        new ResultPair("?action=view", true), 
        new ResultPair("?action=edit&mode=up", true),
        new ResultPair("", true) 
    };

    ResultPair[] testScheme = { 
        new ResultPair("http", true), 
        new ResultPair("ftp", false),
        new ResultPair("httpd", false), 
        new ResultPair("telnet", false) 
    };

    Object[] testUrlParts = { 
        testUrlScheme, 
        testUrlAuthority, 
        testUrlPort, 
        testPath, 
        //testUrlPathOptions, 
        testUrlQuery 
    };

    Object[] testUrlPartsOptions = { 
        testUrlScheme, 
        testUrlAuthority, 
        testUrlPort, 
        testUrlPathOptions, 
        testUrlQuery 
    };

    

    int[] testPartsIndex = { 0, 0, 0, 0, 0 };

    public UrlValidatorTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
        for (int index = 0; index < testPartsIndex.length - 1; index++) {
            testPartsIndex[index] = 0;
        }
    }

    private static boolean incrementTestPartsIndex(int[] testPartsIndex, Object[] testParts) {
        boolean carry = true; // add 1 to lowest order part.
        boolean maxIndex = true;
        for (int testPartsIndexIndex = testPartsIndex.length - 1; testPartsIndexIndex >= 0; --testPartsIndexIndex) {
            int index = testPartsIndex[testPartsIndexIndex];
            ResultPair[] part = (ResultPair[]) testParts[testPartsIndexIndex];
            if (carry) {
                if (index < part.length - 1) {
                    index++;
                    testPartsIndex[testPartsIndexIndex] = index;
                    carry = false;
                } else {
                    testPartsIndex[testPartsIndexIndex] = 0;
                    carry = true;
                }
            }
            maxIndex &= (index == (part.length - 1));
        }
        return (!maxIndex);
    }

    

    public void testIsValid() {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        do {
            StringBuilder testBuffer = new StringBuilder();
            boolean expected = true;
            for (int testPartsIndexIndex = 0; testPartsIndexIndex < testPartsIndex.length; ++testPartsIndexIndex) {
                int index = testPartsIndex[testPartsIndexIndex];
                ResultPair[] part = (ResultPair[]) testUrlParts[testPartsIndexIndex];
                testBuffer.append(part[index].item);
                System.out.println(part[index].item + " = " + part[index].valid);
                expected &= part[index].valid;
            }
            String url = testBuffer.toString();
            boolean result = urlVal.isValid(url);
            System.out.println(url);
            assertEquals(url, expected, result);
        } while (incrementTestPartsIndex(testPartsIndex, testUrlParts));
    }

    public void testIsValidScheme() {
        System.out.println("\n testIsValidScheme() ");
        String[] schemes = { "http", "gopher" };
        // UrlValidator urlVal = new UrlValidator(schemes,false,false,false);
        UrlValidator urlVal = new UrlValidator(schemes, 0);
        for (int sIndex = 0; sIndex < testScheme.length; sIndex++) {
            ResultPair testPair = testScheme[sIndex];
            System.out.println(testPair.item + " = " + testPair.valid);
            boolean result = urlVal.isValidScheme(testPair.item);
            assertEquals(testPair.item, testPair.valid, result);
        }
        System.out.println();
    }


    public void testManual1() {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        assertEquals(true, urlVal.isValid("http://www.google.com:80/test1/?action=view"));
    }

    public void testManual2() {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        assertEquals(true, urlVal.isValid("http://www.google.com:#/test1?action=view"));
    }

    public void testManual3() {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        assertEquals(false, urlVal.isValid("http://www.google.com/..?action=view"));
    }

    public void testManual4() {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        assertEquals(false, urlVal.isValid("http://256.256.256.256:80/test1?action=view"));
    }

    public void testManual5() {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        assertEquals(true, urlVal.isValid("http://0.0.0.0:80/test1?action=view"));

    }

    public void testManual6() {
        String[] schemes = { "http", "gopher" };
        UrlValidator urlVal = new UrlValidator(schemes, 0);
        boolean result = urlVal.isValidScheme("http");
        assertEquals("http", true, result);
    }


    public static void main(String[] argv) {
        UrlValidatorTest fct = new UrlValidatorTest("url test");
        fct.testManual1();
        fct.testManual2();
        fct.testManual3();
        fct.testManual4();
        fct.testManual5();
        //fct.testManual6();
        //fct.setUp();
        //fct.testIsValid();
        //fct.setUp();
        //fct.testIsValidScheme();
    }
}
