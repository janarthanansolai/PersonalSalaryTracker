package com.example.leetcode.Folder;

public class StringCompression {

    public static void main(String[] args) {
        String s = "aaabbbcc";
        char[] chr=new char[Integer.parseInt("a")];
        System.out.println(compress(chr)); // Output: "a3b3c2"
    }

    private static int compress(char[] chars) {

        char[] ch=new char[chars.length];
        int count = 1;
        int arrCount=0;

        for (int i = 0; i < chars.length; i++) {
            if (i + 1 < chars.length && chars[i] == chars[i+1]) {
                count++;
            } else {
                // compressed.append(s.charAt(i));
                ch[arrCount]=chars[i];
                arrCount++;
                if (count > 1) {
                    ch[arrCount]=(char)(count+48);
                    arrCount++;

                }
                count = 1; // Reset count for the next character
            }
        }

        // Check if the compressed string is shorter than the original
        if (arrCount< chars.length) {
             System.out.println(ch);
            return arrCount-1;
        } else {
             System.out.println(ch);
            return arrCount;
        }


    }
}
