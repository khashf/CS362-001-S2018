The test produces wrong result and the program produces correct result
```
Tests run: 1, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.028 sec <<< FAILURE! - in finalprojectB.UrlValidatorTest
testIsValid(finalprojectB.UrlValidatorTest)  Time elapsed: 0.014 sec  <<< FAILURE!
junit.framework.AssertionFailedError: http://www.google.com/test1?action=view expected:<false> but was:<true>
	at junit.framework.Assert.fail(Assert.java:57)
	at junit.framework.Assert.failNotEquals(Assert.java:329)
	at junit.framework.Assert.assertEquals(Assert.java:78)
	at junit.framework.Assert.assertEquals(Assert.java:174)
	at junit.framework.TestCase.assertEquals(TestCase.java:333)
	at finalprojectB.UrlValidatorTest.testIsValid(UrlValidatorTest.java:150)
```
Further checking, I found that every ResultPair.valid value in my test data has been flipped to opposite boolean value. So I checked the ResultPair class

Turn out there is a bug in ResultPair class.
The change is from
```
this.valid = valid;
```
to
```
this.valid = !valid;
```
Lesson learned is that never only check/test the main class, check/test the supporting class too.

===============================================================

Check the wrong authority
www.google.com:80

```
if ("http".equals(scheme)) {// Special case - file: allows an empty authority
            if (authority != null) {
                if (authority.contains(":")) { // but cannot allow trailing :
                    return false;
                }
            }
            // drop through to continue validation
        } else { // not file:
            // Validate the authority
            if (!isValidAuthority(authority)) {
                return false;
            }
        }
```
Bug: Change from `if ("file".equals(scheme))` to `if ("http".equals(scheme))`

===============================================================

DomainValidator line 166
`return isValidTld(groups[0]);` to `return !isValidTld(groups[0]);`

=================================================================
Error:
```
http:// = true
www.google.com = true
:80 = true
/test1/ = true
?action=view = true
http://www.google.com:80/test1/?action=view
Tests run: 2, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.025 sec <<< FAILURE! - in finalprojectB.UrlValidatorTest
testIsValid(finalprojectB.UrlValidatorTest)  Time elapsed: 0.006 sec  <<< FAILURE!
junit.framework.AssertionFailedError: http://www.google.com:80/test1/?action=view expected:<true> but was:<false>
	at junit.framework.Assert.fail(Assert.java:57)
	at junit.framework.Assert.failNotEquals(Assert.java:329)
	at junit.framework.Assert.assertEquals(Assert.java:78)
	at junit.framework.Assert.assertEquals(Assert.java:174)
	at junit.framework.TestCase.assertEquals(TestCase.java:333)
	at finalprojectB.UrlValidatorTest.testIsValid(UrlValidatorTest.java:174)
```

Bad
private static final String PATH_REGEX = "^(/[-\\w:@&?=+,.!*'%$_;\\(\\)]*)?$";

Good
private static final String PATH_REGEX = "^(/[-\\w:@&?=+,.!/~*'%$_;\\(\\)]*)?$";

==================================================================
Related Error:
```
Running finalprojectB.UrlValidatorTest
http:// = true
www.google.com = true
:80 = true
/test1 = true
?action=view = true
Tests run: 2, Failures: 0, Errors: 2, Skipped: 0, Time elapsed: 0.026 sec <<< FAILURE! - in finalprojectB.UrlValidatorTest
testManual(finalprojectB.UrlValidatorTest)  Time elapsed: 0.011 sec  <<< ERROR!
java.lang.ExceptionInInitializerError: null
	at finalprojectB.RegexValidator.<init>(RegexValidator.java:121)
	at finalprojectB.RegexValidator.<init>(RegexValidator.java:96)
	at finalprojectB.RegexValidator.<init>(RegexValidator.java:83)
	at finalprojectB.DomainValidator.<init>(DomainValidator.java:108)
	at finalprojectB.DomainValidator.<clinit>(DomainValidator.java:96)
	at finalprojectB.UrlValidator.isValidAuthority(UrlValidator.java:394)
	at finalprojectB.UrlValidator.isValid(UrlValidator.java:328)
	at finalprojectB.UrlValidatorTest.testManual(UrlValidatorTest.java:155)

testIsValid(finalprojectB.UrlValidatorTest)  Time elapsed: 0 sec  <<< ERROR!
java.lang.NoClassDefFoundError: Could not initialize class finalprojectB.DomainValidator
	at finalprojectB.UrlValidator.isValidAuthority(UrlValidator.java:394)
	at finalprojectB.UrlValidator.isValid(UrlValidator.java:328)
	at finalprojectB.UrlValidatorTest.testIsValid(UrlValidatorTest.java:172)


Results :

Tests in error: 
  UrlValidatorTest.testManual:155 » ExceptionInInitializer
  UrlValidatorTest.testIsValid:172 » NoClassDefFound Could not initialize class ...
```

Bad
`if (regexs != null || regexs.length == 0) {`
Good
`if (regexs == null || regexs.length == 0) {`

==================================

Related Error:
```
Running finalprojectB.UrlValidatorTest
http:// = true
www.google.com = true
:80 = true
/test1 = true
?action=view = true
Tests run: 2, Failures: 0, Errors: 2, Skipped: 0, Time elapsed: 0.027 sec <<< FAILURE! - in finalprojectB.UrlValidatorTest
testManual(finalprojectB.UrlValidatorTest)  Time elapsed: 0.014 sec  <<< ERROR!
java.lang.NullPointerException: null
	at finalprojectB.RegexValidator.match(RegexValidator.java:165)
	at finalprojectB.DomainValidator.isValid(DomainValidator.java:164)
	at finalprojectB.UrlValidator.isValidAuthority(UrlValidator.java:413)
	at finalprojectB.UrlValidator.isValid(UrlValidator.java:328)
	at finalprojectB.UrlValidatorTest.testManual(UrlValidatorTest.java:155)

testIsValid(finalprojectB.UrlValidatorTest)  Time elapsed: 0 sec  <<< ERROR!
java.lang.NullPointerException: null
	at finalprojectB.RegexValidator.match(RegexValidator.java:165)
	at finalprojectB.DomainValidator.isValid(DomainValidator.java:164)
	at finalprojectB.UrlValidator.isValidAuthority(UrlValidator.java:413)
	at finalprojectB.UrlValidator.isValid(UrlValidator.java:328)
	at finalprojectB.UrlValidatorTest.testIsValid(UrlValidatorTest.java:172)


Results :

Tests in error: 
  UrlValidatorTest.testManual:155 » NullPointer
  UrlValidatorTest.testIsValid:172 » NullPointer

Tests run: 2, Failures: 0, Errors: 2, Skipped: 0

```

Bug:

Bad
`for (int i = 0; i < regexs.length-1; i++) {`
Good
`for (int i = 0; i < regexs.length; i++) {`

=================================================================
Error:
```
http:// = true
0.0.0.0 = true
:80 = true
/test1 = true
?action=view = true
Tests run: 2, Failures: 0, Errors: 1, Skipped: 0, Time elapsed: 0.083 sec <<< FAILURE! - in finalprojectB.UrlValidatorTest
testIsValid(finalprojectB.UrlValidatorTest)  Time elapsed: 0.06 sec  <<< ERROR!
java.lang.NullPointerException: null
	at finalprojectB.UrlValidator.isValidAuthority(UrlValidator.java:416)
	at finalprojectB.UrlValidator.isValid(UrlValidator.java:328)
	at finalprojectB.UrlValidatorTest.testIsValid(UrlValidatorTest.java:171)


Results :

Tests in error: 
  UrlValidatorTest.testIsValid:171 » NullPointer

```
InetAddressValidator line 68

Bad:
`return null`

Good:
`return VALIDATOR`

===============================================================
Error:
```
http:// = true
0.0.0.0 = true
:80 = true
/test1 = true
?action=view = true
http://0.0.0.0:80/test1?action=view
Tests run: 2, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.082 sec <<< FAILURE! - in finalprojectB.UrlValidatorTest
testIsValid(finalprojectB.UrlValidatorTest)  Time elapsed: 0.063 sec  <<< FAILURE!
junit.framework.AssertionFailedError: http://0.0.0.0:80/test1?action=view expected:<true> but was:<false>
	at junit.framework.Assert.fail(Assert.java:57)
	at junit.framework.Assert.failNotEquals(Assert.java:329)
	at junit.framework.Assert.assertEquals(Assert.java:78)
	at junit.framework.Assert.assertEquals(Assert.java:174)
	at junit.framework.TestCase.assertEquals(TestCase.java:333)
	at finalprojectB.UrlValidatorTest.testIsValid(UrlValidatorTest.java:173)


Results :

Failed tests: 
  UrlValidatorTest.testIsValid:173 http://0.0.0.0:80/test1?action=view expected:<true> but was:<false>

```

It overcame NullPointerException but stuck right at that test case

Bad
if (groups != null) {

Good
if (groups == null) {

============================================================

Error:
```
http:// = true
256.256.256.256 = false
:80 = true
/test1 = true
?action=view = true
http://256.256.256.256:80/test1?action=view
Tests run: 2, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.124 sec <<< FAILURE! - in finalprojectB.UrlValidatorTest
testIsValid(finalprojectB.UrlValidatorTest)  Time elapsed: 0.099 sec  <<< FAILURE!
junit.framework.AssertionFailedError: http://256.256.256.256:80/test1?action=view expected:<false> but was:<true>
	at junit.framework.Assert.fail(Assert.java:57)
	at junit.framework.Assert.failNotEquals(Assert.java:329)
	at junit.framework.Assert.assertEquals(Assert.java:78)
	at junit.framework.Assert.assertEquals(Assert.java:174)
	at junit.framework.TestCase.assertEquals(TestCase.java:333)
	at finalprojectB.UrlValidatorTest.testIsValid(UrlValidatorTest.java:173)

```

InetAddressValidator line 107
Bad
if (iIpSegment > IPV4_MAX_OCTET_VALUE) {
            		return true;
}
Good
if (iIpSegment > IPV4_MAX_OCTET_VALUE) {
            		return false;
}

======================
