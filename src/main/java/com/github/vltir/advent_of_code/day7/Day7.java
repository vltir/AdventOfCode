package com.github.vltir.advent_of_code.day7;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;

import java.util.*;

public class Day7 implements Day {
    boolean stage2;

    @Override
    public String run(boolean stage2) {
        this.stage2 = stage2;
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day7/input.txt");
        List<Hand> hands = new ArrayList<>(parseHand(inputList));
        Collections.sort(hands);
        int sum = 0;
        for (int i = 0; i < hands.size(); i++) {
            Hand hand = hands.get(i);
            sum += hand.bid * (i + 1);
        }
        return sum + "";
    }

    private List<Hand> parseHand(List<String> inputList) {
        return inputList.stream().map(l -> new Hand(l.split(" +")[0], Integer.parseInt(l.split(" +")[1]), stage2)).toList();
    }


    private record Hand(String cards, int bid, boolean stage2) implements Comparable<Hand> {


        @Override
        public int compareTo(Hand o) {
            if (isFiveOfAKind() && !o.isFiveOfAKind()) return 1;
            if (!isFiveOfAKind() && o.isFiveOfAKind()) return -1;
            if (isFourOfAKind() && !o.isFourOfAKind()) return 1;
            if (!isFourOfAKind() && o.isFourOfAKind()) return -1;
            if (isFullHouse() && !o.isFullHouse()) return 1;
            if (!isFullHouse() && o.isFullHouse()) return -1;
            if (isThreeOfAKind() && !o.isThreeOfAKind()) return 1;
            if (!isThreeOfAKind() && o.isThreeOfAKind()) return -1;
            if (isTwoPair() && !o.isTwoPair()) return 1;
            if (!isTwoPair() && o.isTwoPair()) return -1;
            if (isOnePair() && !o.isOnePair()) return 1;
            if (!isOnePair() && o.isOnePair()) return -1;
            for (int i = 0; i < cards.length(); i++) {
                if (mapToValue(cards.charAt(i)) > o.mapToValue(o.cards.charAt(i))) return 1;
                if (mapToValue(cards.charAt(i)) < o.mapToValue(o.cards.charAt(i))) return -1;
            }
            return 0;
        }

        private boolean isFiveOfAKind() {
            return Arrays.stream(occurrences()).anyMatch(c -> c >= 5);
        }

        private boolean isFourOfAKind() {
            return Arrays.stream(occurrences()).anyMatch(c -> c >= 4);
        }

        private boolean isFullHouse() {
            boolean pair = false;
            boolean triple = false;
            for (int occurrence : occurrences()) {
                if (occurrence == 3) triple = true;
                if (occurrence == 2) pair = true;
            }
            return pair && triple;
        }

        private boolean isThreeOfAKind() {
            return Arrays.stream(occurrences()).anyMatch(c -> c >= 3);
        }

        private boolean isTwoPair() {
            int pairs = 0;
            for (int occurrence : occurrences()) {
                if (occurrence == 2) pairs++;
            }
            return pairs >= 2;
        }

        private boolean isOnePair() {
            return Arrays.stream(occurrences()).anyMatch(c -> c >= 2);
        }


        private int[] occurrences() {
            char[] cardChars = cards.toCharArray();
            int[] occurrence = new int[13];
            for (char cardChar : cardChars) {
                occurrence[mapToValue(cardChar)]++;
            }
            if (stage2) {
                int jacks = occurrence[0];
                occurrence[0]=0;
                int maxIndex = 1;
                for (int i = 1; i < occurrence.length; i++) {
                    if (occurrence[i] > occurrence[maxIndex]) {
                        maxIndex = i;
                    }
                }
                occurrence[maxIndex]+=jacks;
            }
            return occurrence;
        }

        private int mapToValue(char cardChar) {
            if (!stage2) {
                return switch (cardChar) {
                    case 'T' -> 8;
                    case 'J' -> 9;
                    case 'Q' -> 10;
                    case 'K' -> 11;
                    case 'A' -> 12;
                    default -> Character.digit(cardChar, 10)-2;
                };
            } else {
                return switch (cardChar) {
                    case 'T' -> 9;
                    case 'J' -> 0;
                    case 'Q' -> 10;
                    case 'K' -> 11;
                    case 'A' -> 12;
                    default -> Character.digit(cardChar, 10)-1;
                };
            }
        }
    }
}
