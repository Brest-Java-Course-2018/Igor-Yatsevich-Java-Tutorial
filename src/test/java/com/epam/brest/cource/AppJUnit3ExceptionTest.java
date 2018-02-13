package com.epam.brest.cource;

import junit.extensions.ExceptionTestCase;

/**
 * Unit test for simple App.
 * Exception test for method "double getDiv (double, double)"
 * For realises tests used this article https://habrahabr.ru/post/120101/
 */
public class AppJUnit3ExceptionTest extends ExceptionTestCase {

    public AppJUnit3ExceptionTest(final String name) {

        super(name, IllegalArgumentException.class);

    }

    public void testGetDiv() {

        final double div = App.getDiv(4.5, 0.0);

    }
}
