package com.example.leetcode.Folder.interview;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InterviewIbm {

//
//    num = “8555122299”
//
//            555, 222 and 99
//
//    which one is the bigger ? ans - 555


    public static void main(String[] args) {


        String num = "janarthanan";

      String cha= num.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                .max(Map.Entry.comparingByValue()).toString();

      num.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(n->n,Collectors.counting()))
                      .entrySet().stream().sorted().max(Map.Entry.comparingByValue()).toString();

        System.out.println(cha);


        String second="8555122299";

//        second.c
    }
}
