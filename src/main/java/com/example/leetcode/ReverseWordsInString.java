package com.example.leetcode;

public class ReverseWordsInString {


    public static void main(String[] args) {
        String s = "the sky is blue";
        System.out.println(reverseWords(s)); // Output: "blue is sky the"
    }

    private static String reverseWords(String s) {
        // Split the string into words
        String[] words = s.split(" ");

        // Initialize a StringBuilder to store the result
        StringBuilder reversed = new StringBuilder();
        String c="";
        // Iterate through the words in reverse order
        for(int i=words.length - 1; i >= 0; i--) {

            c+= words[i].trim()+" ";



        }


        return c.toString().trim();

    }
}
