package edu.byu.cs203.junit.advanced;

public class Complex {

    static final double PRECISION = 0.00001;
    static final double LN_10 = 2.3025851;

    Complex() {}

    /**
     * Returns the factorial of numbers 0 - 20.
     * <p>
     * There is one bug in this method.
     *
     * @param number A number 0 - 20
     * @return The factorial of the parameter given.
     */

    //added provisions to return if number greater than 20
    //fixed the bug, was not allowing for final multiplication for factorial for normal cases
    public long factorial(long number) {
        if (number < 0) {
            throw new ArithmeticException("Cannot calculate the factorial of a negative number");
        } else if (number > 20) {
            return 0;
        }

        long factorial = 1L;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }

        return factorial;
    }

    /**
     * Returns the square root of a number.
     * <p>
     * Special cases:
     * <ul>
     * <li>If the number is less than 0 or is NaN (not a number), then {@link Double#NaN} is returned.
     * <li>If the number is positive infinity, then {@link Double#POSITIVE_INFINITY} is returned.
     * <li>If the number is 0, then the parameter itself gets returned (0 as a double in Java can be + or -).
     * </ul>
     * <p>
     * There is one edge case bug in this method.
     *
     * @param number Any number of the type double.
     * @return The square root of the number parameter, with the special cases noted
     * above.
     */
    //added the check to see if the edge case 0 happened, to return the number 0
    public double squareRoot(double number) {
        if (number < 0) {
            return Double.NaN;
        } else if (Double.isNaN(number) || number == Double.POSITIVE_INFINITY || number == 0) {
            return number;
        }

        double t;
        double squareRoot = number / 2;
        do {
            t = squareRoot;
            squareRoot = (t + (number / t)) / 2;
        } while ((t - squareRoot) != 0);

        return squareRoot;
    }

    /**
     * Returns the natural log to a precision defined in the static variable {@link #PRECISION Complex.PRECISION}
     * <p>
     * There are two known bugs here. You will find the bugs if you test the edge cases.
     * <p>
     * Documentation of Algorithm:
     * https://docs.google.com/document/d/1XIbWP1sYM1p2e0qJ0hIfJd82nHnEJxN5pcYEhNaxKGs
     *
     * @param number The number to operate on. 0 would return negative infinity.
     *               Negative numbers would return NaN.
     * @return ln(number) to a precision of 0.00005.
     */
    //fixed edge cases so input of 0 no longer triggers if clause
    public double ln(double number) {
        if (number < 0) {
            return Double.NaN;
        } else if (number == 0) {
            return Double.NEGATIVE_INFINITY;
        }

        // Get scientific notation using Strings (for simplicity)
        String scientificNotation = String.format("%e", number);
        int offset = scientificNotation.indexOf("e");
        double coefficient = Double.parseDouble(scientificNotation.substring(0, offset));
        int exponent = Integer.parseInt(scientificNotation.substring(offset + 1));

        // Take care of edge case when coefficient is 1 (like when number == 1000)
        if (coefficient == 1.0) {
            coefficient = 10.0;
            exponent++;
        }

        // Using square root to get number smaller.
        coefficient = squareRoot(coefficient);

        double y = (coefficient - 1) / (coefficient + 1);
        return 2 * lnSeries(y) + (exponent * LN_10);
    }

    /**
     * You DO NOT need to test this method. It is a helper method for
     * {@link Complex#ln ln()}.
     * <p>
     * There are no known bugs in this method.
     *
     * @param y The number to be calculated in the series
     * @return Series calculation of y.
     */
    private double lnSeries(double y) {
        double prev;
        double result = 0;
        int iteration = 0;
        do {
            prev = result;
            double power = 2 * iteration + 1;
            result += (Math.pow(y, power) / power);
            iteration++;
        } while (Math.abs(result - prev) >= PRECISION);
        return result * 2;
    }
}