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

    private static double getMedianOfArrays(int[] nums1, int[] nums2) {
        int total_length = nums1.length + nums2.length;
        boolean odd = total_length % 2 == 0 ? true : false;
        int count_itteration = (odd) ? (total_length / 2 + 1) : ((total_length + 1) / 2);
        int[] target = new int[count_itteration];
        int index1 = 0, index2 = 0, i = 0;
        while (i < count_itteration) {
            if (index1 < nums1.length && index2 < nums2.length) {
                if (nums1[index1] < nums2[index2]) {
                    target[i] = nums1[index1];
                    index1++;
                } else {
                    target[i] = nums2[index2];
                    index2++;

                }
            } else {
                if (index1 == nums1.length && index2 < nums2.length) {
                    target[i] = nums2[index2++];
                }
                if (index2 == nums2.length && index1 < nums1.length) {
                    target[i] = nums1[index1++];
                }
            }
            i++;
        }
        return (odd)
                ? (double) (target[count_itteration - 1] + target[count_itteration - 2]) / 2
                : (double) target[count_itteration - 1];
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
