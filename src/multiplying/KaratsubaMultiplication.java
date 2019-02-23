package multiplying;

import java.util.Scanner;

// Реализовать умножение чисел в столбик - методом Карацубы
/* Пусть необходимо перемножить числа X и Y.
   Представим их в виде:
        X = aT + b
        Y = cT + d
   T - можно рассматривать, как "отступ" от "конца" числа или величину сдвига
   (сколько нулей нужно добавить в конце числа).
   Т.е. если мы говорим о десятичной системе исчисления, то T - некоторая степень 10-ти.

   Умножение "в столбик" будет выглядеть следующим образом:
        XY = (aT + b)(cT + d) = acT2 + (ad + bc)T + bd
   Таким образом мы выполняем 4 операции умножения.

   Теперь рассмотрим умножение методом Карацубы:
        XY =  acT2 + (ad + bc)T + bd = acT2 + ((a+b)(c+d) - ac - bd)T + bd
   Заметим, что выполняются три операции умножения, вместо четырех.
   Таким образом сложность вычислений снижается с N^2 до N*Log(3).
    */
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
