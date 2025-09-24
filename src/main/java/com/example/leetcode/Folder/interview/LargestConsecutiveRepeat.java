package com.example.leetcode.Folder.interview;
import java.util.*;
import java.util.stream.*;

public class LargestConsecutiveRepeat {

    public static String findLargestConsecutiveRepeat(String input) {
        List<String> groups = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        current.append(input.charAt(0));

        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == input.charAt(i - 1)) {
                current.append(c);
            } else {
                groups.add(current.toString());
                current = new StringBuilder().append(c);
            }
        }
        groups.add(current.toString()); // add the last group

        // Use Stream to find the longest & numerically largest group
        return groups.stream()
                .max(Comparator
                        .comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .orElse("");
    }

    public static void main(String[] args) {
        String input = "8555332229";
        String result = findLargestConsecutiveRepeat(input);
        System.out.println("Output: " + result); // Output: 555
    }
}

