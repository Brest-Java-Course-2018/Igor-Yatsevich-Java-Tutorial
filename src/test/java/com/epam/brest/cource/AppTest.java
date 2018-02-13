package com.epam.brest.cource;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 * For realises tests used this article https://habrahabr.ru/post/120101/
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    /**
     * Test "double getSum (double, double)" method;
     */
    public void testGetSum() {

        assertEquals(2.0, App.getSum(1.0, 1.0));
        assertEquals(17.1, App.getSum(10.8, 6.3));
        assertEquals(-5.0, App.getSum(1.0, -6.0));

    }

    /**
     * Test "boolean isEqual (String, String)" method;
     */
    public void testIsEqual() {

        assertTrue(App.isEqual("TEST", "TEST"));
        assertTrue(App.isEqual("JAVA", "JAVA"));

        assertFalse(App.isEqual("TEST", "test"));
        assertFalse(App.isEqual("Java", "JAVA"));

    }

    /**
     * Test "double getDiv (double, double)" method
     */
    public void testGetDiv() {

        assertEquals(1.0, App.getDiv(5.0, 5.0));
        assertEquals(2.0, App.getDiv(5.0, 2.5));

    }

}

