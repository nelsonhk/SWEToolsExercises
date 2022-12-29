package edu.byu.cs203.junit.advanced;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {

    Complex complexInstance = new Complex();

    @Nested
    @DisplayName("Testing Factorial")
    class factorial {
        @DisplayName("Testing Normal Case")
        @ParameterizedTest
        @CsvSource({"5, 120", "4, 24", "2, 2"})
        void testingNormal(long input, long factorial) {
            assertEquals(factorial, complexInstance.factorial(input));
        }

        @DisplayName("Testing Edges")
        @ParameterizedTest
        @CsvSource({"0, 1", "21, 0"})
        void testingEdges(long input, long factorial) {
            assertEquals(factorial, complexInstance.factorial(input));
        }

        @DisplayName("Test Factorial Exception")
        @ParameterizedTest
        @CsvSource({"-1"})
        void testFactorialException(long input) {
            Exception exception = assertThrows(ArithmeticException.class, () -> {
                complexInstance.factorial(input);
            });

            String expectedMessage = "Cannot calculate";
            String actualMessage = exception.getMessage();

            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Nested
    @DisplayName("Testing SquareRoot")
    class squareRoot {
        @DisplayName("Testing Normal Case")
        @ParameterizedTest
        @CsvSource({"16.0, 4.0", "145.0, 12.04159"})
        void testingNormal(double input, double squareRoot) {
            assertEquals(squareRoot, complexInstance.squareRoot(input), complexInstance.PRECISION);
        }

        @DisplayName("Testing Edges")
        @ParameterizedTest
        @CsvSource({"-1, NaN", "NaN, NaN", "0, 0"})
        void testingEdges(double input, double squareRoot) {
            assertEquals(squareRoot, complexInstance.squareRoot(input));
        }
    }

    @Nested
    @DisplayName("Testing Ln")
    class ln {
        @DisplayName("Testing Normal Case")
        @ParameterizedTest
        @CsvSource({"10"})
        void testingNormal(double input) {
            assertEquals(complexInstance.LN_10, complexInstance.ln(input), complexInstance.PRECISION);
        }

        @DisplayName("Testing Edge Cases")
        @ParameterizedTest
        @CsvSource({"0, Double.NEGATIVE_INFINITY", "-1, NaN"})
        void testingEdges(double input, double ln) {
            assertEquals(ln, complexInstance.ln(input), complexInstance.PRECISION);
        }


    }

}