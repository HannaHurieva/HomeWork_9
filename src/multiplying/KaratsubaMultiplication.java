package multiplying;

import java.util.Scanner;

// Реализовать умножение чисел в столбик - методом Карацубы
public class KaratsubaMultiplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input the numbers X and Y for multiplication: ");
        long x = input.nextLong();
        long y = input.nextLong();
        long result = karatsuba(x, y);
        System.out.println("actual    " + result);
        System.out.println("expected  " + x * y);
    }

    private static long karatsuba(long x, long y) {
        if (x < 10 || y < 10) return x * y;
        int degree = Math.max(getSize(x), getSize(y));
        degree = (degree / 2) + (degree % 2);
        long offset = (long) Math.pow(10, degree);

        long a = (long) (x / offset);
        long b = (long) (x - a * offset);
        long c = (long) (y / offset);
        long d = (long) (y - c * offset);

        long first = karatsuba(a, c);
        long second = karatsuba(b, d);
        long third = karatsuba((a + b), (c + d));

        return (long)
                (first * (long) (Math.pow(10, 2 * degree)) + (third - first - second) * offset + second);
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
