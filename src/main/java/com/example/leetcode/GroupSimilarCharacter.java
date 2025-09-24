package com.example.leetcode;

import java.util.*;

public class GroupSimilarCharacter {

//    {eat,ate,tea,ytes,tesy,uyte,teyu}
//
//
//    {[eat,ate,tea],[ytes,tesy],[uyte,teyu]}

    public static void main(String[] args) {
        String[] words = {"eat", "ate", "tea", "ytes", "tesy", "uyte", "teyu"};
        System.out.println(groupSimilarCharacters(words));

    }

    private static List<List<String>> groupSimilarCharacters(String[] words) {

        // Create a map to group words by their sorted character representation
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            // Sort the characters in the word to create a key
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);

            // Add the word to the corresponding list in the map
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }
        return Collections.singletonList(Collections.singletonList(map.values().toString()));
    }
}
