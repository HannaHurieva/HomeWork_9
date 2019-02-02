package multiplying;

import java.util.Scanner;

public class MultiplyInColumn {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input the numbers X and Y for multiplication in a column: ");
        long x = input.nextLong();
        long y = input.nextLong();
        multiplyInColumn(x, y);
    }

    private static void multiplyInColumn(long x, long y) {
        int length_X = getSize(x);
        int length_Y = getSize(y);
        int indent = length_X + length_Y;
        long longest_number, second_number;
        if (length_X >= length_Y) {
            longest_number = x;
            second_number = y;
        } else {
            longest_number = y;
            second_number = x;
        }
        int length = getSize(second_number);
        int[] arrayOfNumbers = getArrayOfNumbers(second_number, length);
        System.out.println("The result of multiplying the numbers: ");
        printNumber(longest_number, indent);
        System.out.println("*");
        printNumber(second_number, indent);
        printLine(indent);
        long result = 0;
        for (int i = 0; i < length; i++) {
            long addend = longest_number * (long) arrayOfNumbers[i] * (long) Math.pow(10, i);
            printAddend(addend, i, indent);
            if (i < length - 1) System.out.println("+");
            result += addend;
        }
        printLine(indent);
        System.out.println(" " + result);
    }

    private static int[] getArrayOfNumbers(long number, int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (number % 10);
            number = number / 10;
        }
        return array;
    }

    private static void printAddend(long addend, int degree, int indent) {
        int spaces = indent - getSize(addend);
        while (spaces > 0) {
            System.out.print(" ");
            spaces--;
        }
        System.out.println((long) (addend / Math.pow(10, degree)));
    }

    private static void printNumber(long num, int indent) {
        int spaces = indent - getSize(num);
        while (spaces > 0) {
            System.out.print(" ");
            spaces--;
        }
        System.out.println(num);
    }

    private static void printLine(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    private static int getSize(long number) {
        int count = 0;
        while (number != 0) {
            count++;
            number /= 10;
        }
        return count;
    }
}