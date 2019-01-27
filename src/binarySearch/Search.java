package binarySearch;

import java.util.Arrays;
import java.util.Scanner;

// Бинарный поиск элемента в отсортированном массиве
public class Search {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input the number of elements: ");
        int number = input.nextInt();

        int[] array = new int[number];
        System.out.println("Input " + number + " items: ");
        for (int i = 0; i < number; i++) {
            array[i] = input.nextInt();
        }
        Arrays.sort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
        System.out.println("Input the number for search: ");
        int item = input.nextInt();
        binarySearchOfItem(array, item);
    }

    private static void binarySearchOfItem(int[] array, int item) {
        int start = 0, end = array.length;
        int index = (start + end) / 2;
        while ((array[index] != item) && (start <= end)) {
            if (array[index] > item) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = (start + end) / 2;
        }
        if (start <= end) {
            System.out.println("The number " + item + " is the " + index + " element of the array");
        } else {
            System.out.println("The number " + item + "is not found");
        }
    }
}