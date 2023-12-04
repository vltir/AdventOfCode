package com.github.vltir.advent_of_code.day4;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day4 implements Day {
    @Override
    public String run(boolean stage2) {
        int sum = 0;
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day4/input.txt");
        int initialLength = inputList.size();
        for (int i = 0; i < inputList.size(); i++) {
            String inputLine = inputList.get(i);
            String removedHeader = inputLine.replace("  ", " 0");
            removedHeader = removedHeader.split(": ")[1];
            String set1String = removedHeader.split(" \\| ")[0];
            String set2String = removedHeader.split(" \\| ")[1];
            Set<String> set1 = Arrays.stream(set1String.split(" ")).collect(Collectors.toSet());
            Set<String> set2 = Arrays.stream(set2String.split(" ")).collect(Collectors.toSet());
            int counter = 0;
            for (String s : set2) {
                if (set1.contains(s)) {
                    counter++;
                }
            }
            if (stage2) {
                for (int j = 0; j < counter; j++) {
                    int cardNumber = Integer.parseInt(inputLine.split(":")[0].split(" +")[1]);
                    if (cardNumber <= initialLength) {
                        inputList.add(inputList.get(cardNumber + j));
                    }
                }
            }
            int worth = counter != 0 ? 1 << (counter - 1) : 0;
            sum += worth;
        }
        if (!stage2) {
            return sum + "";
        }
        return inputList.size() + "";
    }
}
