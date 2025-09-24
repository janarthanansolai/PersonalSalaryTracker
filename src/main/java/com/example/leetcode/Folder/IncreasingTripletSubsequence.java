package com.example.leetcode.Folder;

public class IncreasingTripletSubsequence {

    public static void main(String[] args) {
        int[] nums = {20,100,10,9,12,5,13};
        System.out.println(increasingTriplet(nums)); // Output: true
    }

    private static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false; // A triplet cannot exist in less than 3 elements
        }

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num; // Update the smallest number
            } else if (num <= second) {
                second = num; // Update the second smallest number
            } else {
                // If we find a number greater than both first and second, we have a triplet
                return true;
            }
        }

        return false; // No increasing triplet found
    }
}
