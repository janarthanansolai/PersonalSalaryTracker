package com.example.leetcode.Folder;

public class ReverseVowelInString {

    public static String reverseVowels(String s) {
        // Convert the string to a character array for easier manipulation
        char[] arr = s.toCharArray();

        // Define a set of vowels for quick lookup
        String vowels = "aeiouAEIOU";

        // Initialize two pointers, one from the beginning and one from the end
        int left = 0, right = arr.length - 1;

        // Loop until the two pointers meet
        while (left < right) {
            // Move the left pointer to the next vowel
            while (left < right && !vowels.contains(arr[left] + "")) {
                left++;
            }

            // Move the right pointer to the previous vowel
            while (left < right && !vowels.contains(arr[right] + "")) {
                right--;
            }

            // Swap the vowels at the left and right pointers
            if (left < right) {
                char temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        // Convert the character array back to a string and return it
        return new String(arr);
    }

    public static void main(String[] args) {
        String s1 = "IceCreAm";
        String s2 = "leetcode";

        System.out.println(reverseVowels(s1)); // Output: "AceCreIm"
        System.out.println(reverseVowels(s2));
        System.out.println((reverseVowels("AMMMMMM")));// Output: "leotcede"
    }
}
