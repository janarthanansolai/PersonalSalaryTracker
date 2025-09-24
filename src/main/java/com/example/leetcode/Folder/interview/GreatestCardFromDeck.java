package com.example.leetcode.Folder.interview;

public class GreatestCardFromDeck {

    public static void main(String[] args) {

        String[] arr = {"2-H", "3-D", "A-C", "5-S", "6-H"};
        String temp = "JQKA";
        int greatestVal = 0;
        String greatestCard = "";
        for (String card : arr) {
            String[] parts = card.split("-");
            String value = parts[0];
            String suit = parts[1];

            int cardValue;
            if (temp.contains(value)) {
                cardValue = 10 + temp.indexOf(value); // J=11, Q=12, K=13, A=14
            } else {
                cardValue = Integer.parseInt(value);
            }

            int suitValue;
            switch (suit) {
                case "H":
                    suitValue = 1;
                    break;
                case "D":
                    suitValue = 2;
                    break;
                case "C":
                    suitValue = 3;
                    break;
                case "S":
                    suitValue = 4;
                    break;
                default:
                    suitValue = 0; // Invalid suit
            }

            int totalValue = cardValue * 10 + suitValue; // Combine value and suit

            if (totalValue > greatestVal) {
                greatestVal = totalValue;
                greatestCard = card;
            }
        }

        System.out.println("Greatest Card: " + greatestCard);


    }
}
