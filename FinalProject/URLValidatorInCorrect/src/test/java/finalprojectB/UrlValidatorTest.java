
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!

public class UrlValidatorTest extends TestCase {

    ResultPair[] testUrlScheme = { 
        new ResultPair("http://", true),
        new ResultPair("ftp://", true),
        new ResultPair("h3t://", true),
        new ResultPair("3ht://", false),
        new ResultPair("http:/", false),
        new ResultPair("http:", false),
        new ResultPair("http/", false),
        new ResultPair("://", false),
        new ResultPair("", true) 
    };

    ResultPair[] testUrlAuthority = {
        new ResultPair("www.google.com", true),
        new ResultPair("go.com", true),
        new ResultPair("go.au", true), 
        new ResultPair("0.0.0.0", true), 
        new ResultPair("255.255.255.255", true),
        new ResultPair("256.256.256.256", false), 
        new ResultPair("255.com", true),
        new ResultPair("1.2.3.4.5", false), 
        new ResultPair("1.2.3.4.", false), 
        new ResultPair("1.2.3", false),
        new ResultPair(".1.2.3.4", false), 
        new ResultPair("go.a", false), 
        new ResultPair("go.a1a", false),
        new ResultPair("go.1aa", false), 
        new ResultPair("aaa.", false), 
        new ResultPair(".aaa", false),
        new ResultPair("aaa", false), 
        new ResultPair("", false) 
    };

    ResultPair[] testUrlPort = { 
        new ResultPair(":80", true), 
        new ResultPair(":65535", true),
        new ResultPair(":0", true), 
        new ResultPair("", true), 
        new ResultPair(":-1", false),
        new ResultPair(":65636", false), 
        new ResultPair(":65a", false) 
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
        new ResultPair("/t123", true),
        new ResultPair("/$23", true), 
        new ResultPair("/..", false), 
        new ResultPair("/../", false),
        new ResultPair("/test1/", true), 
        new ResultPair("/#", false), 
        new ResultPair("", true),
        new ResultPair("/test1/file", true), 
        new ResultPair("/t123/file", true), 
        new ResultPair("/$23/file", true),
        new ResultPair("/../file", false), 
        new ResultPair("/..//file", false), 
        new ResultPair("/test1//file", true),
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

    public void testManual() {
        UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        assertEquals(true, urlVal.isValid("http://www.google.com:80/test1/?action=view"));
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

    // public void testIsValidScheme() {
    //     System.out.println("\n testIsValidScheme() ");
    //     String[] schemes = { "http", "gopher" };
    //     // UrlValidator urlVal = new UrlValidator(schemes,false,false,false);
    //     UrlValidator urlVal = new UrlValidator(schemes, 0);
    //     for (int sIndex = 0; sIndex < testScheme.length; sIndex++) {
    //         ResultPair testPair = testScheme[sIndex];
    //         boolean result = urlVal.isValidScheme(testPair.item);
    //         assertEquals(testPair.item, testPair.valid, result);
    //     }
    //     System.out.println();
    // }

    public static void main(String[] argv) {
        UrlValidatorTest fct = new UrlValidatorTest("url test");
        fct.testManual();
        //fct.setUp();
        //fct.testIsValid();
        //fct.setUp();
        //fct.testIsValidScheme();
    }
}
