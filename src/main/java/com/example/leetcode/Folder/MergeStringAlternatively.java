package com.example.leetcode.Folder;

import static java.lang.Math.max;

public class MergeStringAlternatively {


    public static void main(String[] args) {
        String word1 = "abc";
        String word2 = "pqr";
        String c="";
       int maxCharCount= max(word1.length(), word2.length());
       for(int i=0; i<maxCharCount; i++){
           if(i<word1.length()){
               c+= word1.charAt(i);
               System.out.print(word1.charAt(i));
           }
           if(i<word2.length()){
              c+= word2.charAt(i);
               System.out.print(word2.charAt(i));
           }
           }
       System.out.println(mergeAlternately(word1, word2));
       System.out.println(" c *** "+c);
    }

    private static String mergeAlternately(String word1, String word2) {

        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i < word1.length() && j < word2.length()) {
            sb.append(word1.charAt(i++));
            sb.append(word2.charAt(j++));
        }
        while (i < word1.length()) {
            sb.append(word1.charAt(i++));
        }
        while (j < word2.length()) {
            sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }
}



