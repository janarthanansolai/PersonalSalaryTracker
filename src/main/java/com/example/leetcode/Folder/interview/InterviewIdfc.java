package com.example.leetcode.Folder.interview;

import java.util.Arrays;

public class InterviewIdfc {

    public static void main(String[] args) {


        String[] arr = {"2-H", "3-D", "4-C", "5-S", "6-H"};

        String temp="JQKA";

        int greatestval=0;
        int firstVal = 0; ;
        int secondVal = 0;

        int firstSecond=0;
        int secondSecond=0;

        for(int i=0;i< arr.length-1;i++){

            String firstValue="";
            String firstValueSecond="";

           firstValue= Arrays.stream(arr[i].split("-")).findFirst().toString();
             firstValueSecond= Arrays.stream(arr[i].split("-")).skip(1) .findFirst().toString();

            if(firstValueSecond.equals("H")){
                firstSecond=1;
            } else if (firstValueSecond.equals("D")) {
                firstSecond=2;
            } else if (firstValueSecond.equals("C")) {
                firstSecond=3;
            } else if (firstValueSecond.equals("S")) {
                firstSecond=4;
            }


            String secondValue;
            String secondValueSecond;

           secondValue= Arrays.stream(arr[i+1].split("-")).findFirst().toString();

             secondValueSecond= Arrays.stream(arr[i+1].split("-")).skip(1) .findFirst().toString();

            if(secondValueSecond.equals("H")){
                secondSecond=1;
            } else if (firstValueSecond.equals("D")) {
                secondSecond=2;
            } else if (firstValueSecond.equals("C")) {
                secondSecond=3;
            } else if (firstValueSecond.equals("S")) {
                secondSecond=4;
            }

//            if(temp.contains(firstValue)){
              if (firstValue.equals( "J")) {
                    firstVal = 11;
                } else if (firstValue.equals("Q")) {
                    firstVal = 12;
                } else if (firstValue.equals("K")) {
                    firstVal = 13;
                } else if (firstValue.equals("A")) {
                    firstVal = 14;
                } else {
                    firstVal = Integer.parseInt(firstValue);
              }

//            }


//            if(temp.contains(secondValue)){
                if (firstValue.equals( "J")) {
                    secondVal = 11;
                } else if (firstValue.equals("Q")) {
                    secondVal = 12;
                } else if (firstValue.equals("K")) {
                    secondVal = 13;
                } else if (firstValue.equals("A")) {
                    secondVal = 14;
                } else {
                    secondVal = Integer.parseInt(secondValue);

                }

//            }


            if(firstVal>secondVal){
              greatestval= i;

            } else if (firstVal < secondVal) {
                greatestval= i++;
            } else {

                if(firstSecond>secondSecond){
                    greatestval= i;

                } else if (firstSecond < secondSecond) {
                    greatestval= i++;
                }
            }



        }

        System.out.println("Greatest Value: " + arr[greatestval]);

    }



}
