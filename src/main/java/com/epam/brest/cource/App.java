package com.epam.brest.cource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static double getSum(double _arg1, double _arg2)
    {

        return _arg1 + _arg2;

    }

    public static boolean isEqual(String _arg1, String _arg2)
    {

        return _arg1.equals(_arg2);

    }

    public static double getDiv(double _arg1, double _arg2)
    {

        if (_arg2 == 0) throw new IllegalArgumentException("Division by zero is not allowed!");

        return _arg1 / _arg2;

    }
}
