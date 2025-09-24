package com.example.leetcode.Folder.interview;

public class findLargestConsecutiveRepeat {

    public static void main(String[] args) {
        String num = "8222555122299";
        System.out.println(findLargestConsecutiveRepeat(num)); // Output: 555
    }

    private static String findLargestConsecutiveRepeat(String num) {
        String largest = "";
        String current = "";

        for (int i = 0; i < num.length(); i++) {
            current += num.charAt(i);
            if (i + 1 < num.length() && num.charAt(i) == num.charAt(i + 1)) {
                continue;
            } else {
                if (current.length() > largest.length() ||
                    (current.length() == largest.length() && current.compareTo(largest) > 0)) {
                    largest = current;
                }
                current = "";
            }
        }

        return largest;
    }
}
