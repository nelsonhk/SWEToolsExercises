package edu.byu.cs203.junit.advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTest {

    Simple simpleInstance = new Simple();

    //this test covers all equivalence classes, including addition between two positive ints, one positive and one negative,
    //and two negative ints
    @DisplayName("Test Add")
    @ParameterizedTest
    @CsvSource({"1, 1", "2, -2", "-3, -3"})
    void testAdd(int num1, int num2) {
        int expectedSum = num1 + num2;
        assertEquals(expectedSum, simpleInstance.add(num1, num2));
    }

    //this test covers all equivalence classes, including subtraction between two positive ints, one positive and one negative,
    //and two negative ints
    @DisplayName("Test Subtract")
    @ParameterizedTest
    @CsvSource({"2, 1", "2, -3", "-3, -3"})
    void testSubtract(int num1, int num2) {
        int expectedDifference = num1 - num2;
        assertEquals(expectedDifference, simpleInstance.subtract(num1, num2));
    }

    //this test covers all equivalence classes, including multiplication between two positive ints, one positive and one negative,
    //two negative ints, and multiplication by zero
    @DisplayName("Test Multiply")
    @ParameterizedTest
    @CsvSource({"2, 1", "0, 3", "3, -3", "-4, -4"})
    void testMultiply(int num1, int num2) {
        int expectedProduct = num1 * num2;
        assertEquals(expectedProduct, simpleInstance.multiply(num1, num2));
    }

    //this test covers all equivalence classes, including division between two positive ints, one positive and one negative,
    //two negative ints
    @DisplayName("Test Divide")
    @ParameterizedTest
    @CsvSource({"2, 1", "-2, 3", "-3, -3"})
    void testDivide(int num1, int num2) {
        int expectedQuotient = num1 / num2;
        assertEquals(expectedQuotient, simpleInstance.divide(num1, num2));
    }

    //this covers arithmetic exception, which only happens when you divide an int by zero
    @DisplayName("Test Divide Exception")
    @ParameterizedTest
    @CsvSource({"3, 0"})
    void testDivideException(int num1, int num2) {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            simpleInstance.divide(num1, num2);
        });

        String expectedMessage = "/ by zero";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    //this test covers all equivalence classes, including two positive ints, one positive and one negative,
    //two negative ints
    @DisplayName("Test PowerOf")
    @ParameterizedTest
    @CsvSource({"2, 1, 2", "3, -1, 1", "-1, -1, 1"})
    void testPowerOf(int num1, int num2, int answer) {
        int expectedValue = answer;
        assertEquals(expectedValue, simpleInstance.power(num1, num2));
    }

}