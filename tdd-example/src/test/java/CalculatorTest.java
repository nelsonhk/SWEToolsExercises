import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void addTest () {
        Assertions.assertEquals(5, calculator.add(2, 3));
        Assertions.assertEquals(9, calculator.add(4, 5));
    }

    @Test
    public void subtractTest() {
        Assertions.assertEquals(3, calculator.subtract(7, 4));
        Assertions.assertEquals(6, calculator.subtract(10, 4));
    }

    @Test
    public void powerTest_PositiveValuePostivePower() {
        Assertions.assertEquals(16, calculator.power(4, 2));
        Assertions.assertEquals(81, calculator.power(9, 2));
    }

    @Test
    public void powerTest_PostiveValueZeroPower() {
        Assertions.assertEquals(1, calculator.power(8, 0));
    }

    @Test
    public void powerTest_PositiveValueNegativePower () {
        Assertions.assertEquals(0.04, calculator.power(5, -2), 0.001);
        Assertions.assertEquals(0.0625, calculator.power(4, -2), 0.001);
    }

    @Test
    public void powerTest_NegativeValuePositivePower () {
        Assertions.assertEquals(9, calculator.power(-3, 2));
    }

    @Test
    public void powerTest_NegativeValueZeroPower () {
        Assertions.assertEquals(1, calculator.power(-3, 0));
    }

    @Test
    public void powerTest_NegativeValueNegativePower () {
        Assertions.assertEquals(0.04, calculator.power(-5, -2), .001);
        Assertions.assertEquals(-0.008, calculator.power(-5, -3), .001);
    }

    @Test
    public void powerTest_ZeroValuePositivePower () {
        Assertions.assertEquals(0, calculator.power(0, 5));
    }

    @Test
    public void powerTest_ZeroValueZeroPower () {
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.power(0, 0));
    }

    @Test
    public void powerTest_ZeroValueNegativePower () {
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.power(0, -3));
    }




}
