import java.util.Scanner;

public class FibonacciSequence {

    public static void main(String[] args) {
        System.out.println("This program was made to find the nth fibonacci number.");
        System.out.print("Enter in 'n': ");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println();

        FibonacciSequence fibonacciSequence = new FibonacciSequence();


        int slowAnswer = fibonacciSequence.fibonacci(n);
        System.out.println("Fibonacci Number: " + Integer.toString(slowAnswer));
    }

    private int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}

