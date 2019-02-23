package multiplying;

import java.util.Scanner;

/*  Перемножение чисел "в столбик" - как учат в школе )
    Неоптимальный алгоритм - О(N^2)
    */
public class MultiplyInColumn {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input the numbers X and Y for multiplication in a column: ");
        long x = input.nextLong();
        long y = input.nextLong();
        printMultiplication(x, y);
    }

    private static void printMultiplication(long x, long y) {
        long first_multiplier, second_multiplier, result;
        if (x >= y) {
            first_multiplier = x;
            second_multiplier = y;
        } else {
            first_multiplier = y;
            second_multiplier = x;
        }
        int length_1 = getSize(first_multiplier);
        int length_2 = getSize(second_multiplier);
        int length = length_1 + length_2 + 1;

        System.out.println("The result of multiplying the numbers: ");
        printNumber(first_multiplier, length);
        System.out.println("*");
        printNumber(second_multiplier, length);
        printLine(length);

        int[] arrayOfNumbers_1 = getArrayOfNumbers(first_multiplier, length_1);
        int[] arrayOfNumbers_2 = getArrayOfNumbers(second_multiplier, length_2);
        long[] addend = getAddendByStep(arrayOfNumbers_1, arrayOfNumbers_2);
        for (int i = 0; i < addend.length; i++) printAddend(addend[i], i, length);

        printLine(length);
        result = resultOfMultiplication(addend);
        printNumber(result, length);
    }

    private static long resultOfMultiplication(long[] addend) {
        long result = 0;
        for (long element :
                addend) {
            result += element;
        }
        return result;
    }

    private static long[] getAddendByStep(int[] first, int[] second) {
        long[] res_by_step = new long[second.length];
        for (int i = 0; i < second.length; i++) {
            long result = 0;
            int[] addend = new int[first.length];
            addend[first.length - 1] = first[0] * second[i] % 10;
            result += addend[first.length - 1] * (long) Math.pow(10, i);

            for (int j = 1; j < first.length - 1; j++) {
                if (first[j] * second[i] >= 10) {
                    addend[first.length - 1 - j] = first[j] * second[i] % 10 + first[j - 1] * second[i] / 10;
                } else addend[first.length - 1 - j] = first[j] * second[i] + first[j - 1] * second[i] / 10;
                result += addend[first.length - 1 - j] * (long) Math.pow(10, i + j);
            }

            addend[0] = first[first.length - 1] * second[i] + first[first.length - 2] * second[i] / 10;
            result += addend[0] * (long) Math.pow(10, i + first.length - 1);

            res_by_step[i] = result;
        }
        return res_by_step;
    }

    private static int[] getArrayOfNumbers(long number, int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (number % 10);
            number /= 10;
        }
        return array;
    }

    private static int getSize(long number) {
        int count = 0;
        while (number != 0) {
            count++;
            number /= 10;
        }
        return count;
    }

    private static void printAddend(long addend, int i, int length) {
        if (i > 0) {
            System.out.println("+");
        }
        int length_addend = getSize(addend);
        int spaces = length - length_addend;
        while (spaces > 0) {
            System.out.print(" ");
            spaces--;
        }
        System.out.print(addend / (long) Math.pow(10, i));
        System.out.println();
    }

    private static void printNumber(long num, int length) {
        int spaces = length - getSize(num);
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
}