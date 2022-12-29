//package main.java;

import java.io.IOException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator();
        Scanner myScanner = new Scanner(System.in);
        String input = myScanner.nextLine();
        String [] inputArr = input.split(" ");

        if ("add".equals(inputArr[0])) {
            int num1 = Integer.parseInt(inputArr[1]);
            int num2 = Integer.parseInt(inputArr[2]);
            System.out.println(calculator.add(num1, num2));
        } else if ("subtract".equals(inputArr[0])) {
            int num1 = Integer.parseInt(inputArr[1]);
            int num2 = Integer.parseInt(inputArr[2]);
            System.out.println(calculator.subtract(num1, num2));
        } else if ("multiply".equals(inputArr[0])) {
            int num1 = Integer.parseInt(inputArr[1]);
            int num2 = Integer.parseInt(inputArr[2]);
            System.out.println(calculator.multiply(num1, num2));
        } else if ("divide".equals(inputArr[0])) {
            int num1 = Integer.parseInt(inputArr[1]);
            int num2 = Integer.parseInt(inputArr[2]);
            System.out.println(calculator.divide(num1, num2));
        } else if ("fibonacci".equals(inputArr[0])) {
            int num = Integer.parseInt(inputArr[1]);
            System.out.println(calculator.fibonacciNumberFinder(num));
        } else if ("binary".equals(inputArr[0])) {
            int num = Integer.parseInt(inputArr[1]);
            System.out.println(calculator.intToBinaryNumber(num));
        }
    }

}
