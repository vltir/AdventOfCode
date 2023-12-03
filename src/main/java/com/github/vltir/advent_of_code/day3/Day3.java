package com.github.vltir.advent_of_code.day3;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 implements Day {
    private char[][] inputCharacters;
    private boolean stage2;

    @Override
    public String run(boolean stage2) {
        this.stage2 = stage2;
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day3/input.txt");
        inputCharacters = setInputCharacters(inputList);
        if (!stage2) {
            int validNumberSum = 0;
            for (int i = 0; i < inputCharacters.length; i++) {
                for (int j = 0; j < inputCharacters[i].length; j++) {
                    NumberWithDigits numberWithDigits = nextNumber(i, j);
                    if (numberWithDigits.digits == 0) continue;
                    if (hasSymbolNeighbor(i, j, numberWithDigits.digits)) {
                        validNumberSum += numberWithDigits.number;
                    }
                    j += numberWithDigits.digits;
                }
            }

            return validNumberSum + "";
        }
        int sumOfRatios = 0;
        for (int i = 0; i < inputCharacters.length; i++) {
            for (int j = 0; j < inputCharacters[i].length; j++) {
                if (isSymbolAt(i, j)) {
                    sumOfRatios += findNearNumber(i, j);
                }
            }

        }
        return sumOfRatios + "";
    }

    private int findNearNumber(int i, int j) {
        Set<NumberWithRoot> numberWithRootSet = new HashSet<>();
        for (int l = Math.max(i - 1, 0); l < Math.min(i + 2, inputCharacters.length); l++) {
            for (int m = Math.max(j - 1, 0); m < Math.min(j + 2, inputCharacters[l].length); m++) {
                var numberWithRoot = findNumberRoot(l, m);
                if (numberWithRoot == null) continue;
                numberWithRootSet.add(numberWithRoot);
            }
        }
        if (numberWithRootSet.size() == 2) {

            return numberWithRootSet.stream().mapToInt(NumberWithRoot::number).reduce(1, (x, y) -> x * y);
        }
        return 0;
    }

    private NumberWithRoot findNumberRoot(int i, int j) {
        if (!isDigitAt(i, j)) return null;
        int k = 0;
        while (j + k >= 0 && isDigitAt(i, j + k)) {
            k--;
        }
        int number = nextNumber(i, j + k + 1).number;
        return new NumberWithRoot(number, i, j + k + 1);
    }

    private record NumberWithRoot(int number, int i, int j) {
    }

    ;


    private boolean hasSymbolNeighbor(int i, int j, int digits) {
        for (int l = Math.max(i - 1, 0); l < Math.min(i + 2, inputCharacters.length); l++) {
            for (int m = Math.max(j - 1, 0); m < Math.min(j + 1 + digits, inputCharacters[l].length); m++) {
                if (isSymbolAt(l, m)) return true;
            }
        }
        return false;
    }

    private boolean isSymbolAt(int l, int m) {
        if (!stage2) {
            return !(Character.isDigit(inputCharacters[l][m]) || inputCharacters[l][m] == '.');
        }
        return inputCharacters[l][m] == '*';
    }

    private NumberWithDigits nextNumber(int i, int j) {
        List<Character> numberList = new ArrayList<>();
        int k = 0;


        while (j + k < inputCharacters[i].length && isDigitAt(i, j + k)) {
            numberList.add(inputCharacters[i][Math.min(j + k, inputCharacters[i].length - 1)]);
            k++;
        }

        if (numberList.isEmpty()) return new NumberWithDigits(0, 0);
        StringBuilder numberString = new StringBuilder();
        for (Character c : numberList) {
            numberString.append(String.valueOf(c));
        }
        return new NumberWithDigits(Integer.parseInt(numberString.toString()), k);
    }

    private record NumberWithDigits(int number, int digits) {
    }

    private boolean isDigitAt(int i, int j) {
        return Character.isDigit(inputCharacters[i][j]);
    }


    private char[][] setInputCharacters(List<String> inputList) {
        char[][] inputCharacters = new char[inputList.size()][inputList.get(0).length()];
        for (int i = 0; i < inputCharacters.length; i++) {
            for (int j = 0; j < inputCharacters[i].length; j++) {
                inputCharacters[i][j] = inputList.get(i).charAt(j);
            }
        }
        return inputCharacters;
    }
}
