package com.example.leetcode.Folder;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] result = productExceptSelf(nums);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    private static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Initialize the result array with 1
        for (int i = 0; i < n; i++) {
            result[i] = 1;
        }

        // Calculate the prefix product
        int prefixProduct = 1;
        for (int i = 0; i < n; i++) {
            result[i] *= prefixProduct;
            prefixProduct *= nums[i];
        }

        // Calculate the suffix product and multiply it with the prefix product
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return result;
    }
}
