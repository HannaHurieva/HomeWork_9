package MedianOfArrays;

import java.util.Arrays;
import java.util.Scanner;

/* Median of Two Sorted Arrays
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
You may assume nums1 and nums2 cannot be both empty.*/
public class Median {
    public static void main(String[] args) {
        int[] nums1 = getInputArray(1);
        Arrays.sort(nums1);
        int[] nums2 = getInputArray(2);
        Arrays.sort(nums2);
        double median = getMedianOfArrays(nums1, nums2);
        System.out.println("The median of two sorted arrays = " + median);
    }

    private static double getMedianOfArrays(int[] arrayFirst, int[] arraySecond) {
        int total_length = arrayFirst.length + arraySecond.length;
        boolean odd = total_length % 2 == 0 ? true : false;
        int count_itteration = (odd) ? (total_length / 2 + 1) : ((total_length + 1) / 2);
        int targetLeft = arrayFirst[0], targetRight = arraySecond[0];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < count_itteration; i++) {
            if (index1 < arrayFirst.length && index2 < arraySecond.length) {
                if (arrayFirst[index1] < arraySecond[index2]) {
                    targetRight = arrayFirst[index1];
                    index1++;
                } else {
                    targetRight = arraySecond[index2];
                    index2++;

                }
            }
            if (i == count_itteration - 2) targetLeft = targetRight;
            if (index1 == arrayFirst.length && index2 < arraySecond.length){
                targetRight = arraySecond[index2++];
                i++;
                if (i == count_itteration - 2) targetLeft = targetRight;
            }
            if (index2 == arraySecond.length && index1 < arrayFirst.length) {
                targetRight = arrayFirst[index1++];
                i++;
                if (i == count_itteration - 2) targetLeft = targetRight;
            }
        }
        double target = (odd) ? (double)(targetLeft + targetRight)/2 : (double) targetRight;
        return target;
    }

    private static int[] getInputArray(int item) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input the number of elements " + item + " array: ");
        int length = input.nextInt();

        int[] array = new int[length];
        System.out.println("Input " + length + " items: ");
        for (int i = 0; i < length; i++) {
            array[i] = input.nextInt();
        }
        return array;
    }
}
