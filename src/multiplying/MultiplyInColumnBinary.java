package multiplying;

import java.math.BigInteger;
import java.util.Scanner;

/*  Метод "Разделяй и властвуй"
    Перемножение чисел в двоичной системе счисления.
    Умножение "в столбик" - О(N^2)
    https://stepik.org/lesson/13247/step/2?unit=3432 (постановка задачи 3:03 - 5:00)
    */
public class MultiplyInColumnBinary {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input the numbers X and Y for multiplication in a column: ");
        int x = input.nextInt();
        int y = input.nextInt();
        String result = multiplyInColumnBinary(x, y);
//        System.out.println("Result of multiplication = " + Integer.parseInt(result,2));
        System.out.println("Result of multiplication = " + new BigInteger(result,2).longValue());
    }

    public static String multiplyInColumnBinary(int x, int y) {
        String first = Integer.toBinaryString(x);
        String second = Integer.toBinaryString(y);
        if (y > x) {
            String buffer = first;
            first = second;
            second = buffer;
        }
        int length = first.length() + second.length();
        System.out.println();
        printToString(first, length);
        System.out.println('*');
        printToString(second, length);
        printLine(length);

        int step = second.length();
        String[] addend = new String[step];
        addend[0] = addend(first, second, step, 0);
        printToString(addend[0], length);
        String buffer;
        String result = addend[0];
        for (int i = 1; i < step; i++) {
            System.out.println("+");
            addend[i] = addend(first, second, step, i);
            printToString(addend[i], length);
            buffer = additionByStep(result, addend[i]);
            result = reverse(buffer);
        }
        printLine(length);
        printToString(result, length);
        return result;
    }

    public static String addend(String first, String second, int step, int item) {
        String addend;
        if (second.charAt(step - 1 - item) == '1') {
            addend = first;
        } else {
            addend = "";
            for (int i = 0; i < first.length(); i++) {
                addend = addend.concat("0");
            }
        }
        for (int j = 0; j < item; j++) {
            addend = addend.concat("0");
        }
        return addend;
    }

    public static String additionByStep(String first, String second) {
        char[] first_term = first.toCharArray();
        char[] second_term = second.toCharArray();
        int i = first.length() - 1;
        int j = second.length() - 1;
        int add = 0, buffer;
        String result = "";

        while (i >= 0) {
            buffer = first_term[i] - 48 + second_term[j] - 48 + add;
            switch (buffer) {
                case 3:
                    result = result.concat("1");
                    add = 1;
                    break;
                case 2:
                    result = result.concat("0");
                    add = 1;
                    break;
                case 1:
                    result = result.concat("1");
                    add = 0;
                    break;
                case 0:
                    result = result.concat("0");
                    add = 0;
                    break;
            }
            if (j == 0) result = result.concat(Integer.toString(add));
            i--;
            j--;
        }
        while (j >= 0) {
            if (second_term[j] - 48 + add == 2) {
                result = result.concat("0");
                add = 1;
                if (j == 0) result = result.concat("1");
            } else {
                if (second_term[j] - 48 + add == 1) {
                    result = result.concat("1");
                } else result = result.concat("0");
            }
            j--;
        }
        return result;
    }

    public static String reverse(String input) {
        String output = "";
        char[] strToChar = input.toCharArray();
        for (int i = input.length() - 1; i >= 0; i--) {
            output = output.concat(String.valueOf(strToChar[i]));
        }
        return output;
    }

    public static void printToString(String str, int length) {
        for (int i = 0; i < (length - str.length()); i++) {
            System.out.print(" ");
        }
        System.out.print(str);
        System.out.println();
    }

    public static void printLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print('-');
        }
        System.out.println();
    }
}
