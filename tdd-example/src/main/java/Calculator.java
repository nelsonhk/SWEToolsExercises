public class Calculator {

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public int subtract(int num1, int num2) {
        return num1 - num2;
    }

    public double power(int num1, int num2) throws ArithmeticException {
        if (num1 == 0 && num2 <= 0) {
            throw new ArithmeticException("Not a number");
        }
        double value = num1;
        if (num2 < 0) {
            value = 1.0 / num1;
            num2 *= -1;
        }
        double result = 1.0;
        for (int i = 0; i < num2; i++) {
            result *= value;
        }
        return result;
    }

}
