package com.github.vltir.advent_of_code.day9;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day9 implements Day {
    private List<List<Integer>> lines = new ArrayList<>();

    public Day9() {
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day9/input.txt");
        parseInput(inputList);
    }

    @Override
    public String run(boolean stage2) {
        int sum = 0;
        for (List<Integer> line : lines) {
            sum += followingOf(line, stage2);
        }
        return sum + "";
    }

    private int followingOf(List<Integer> line, boolean front) {
        if (line.stream().allMatch(i -> i == 0)) return 0;
        List<Integer> steps = new ArrayList<>();
        for (int i = 0; i < line.size() - 1; i++) {
            steps.add(line.get(i + 1) - line.get(i));
        }
        return line.get(front ? 0 : line.size() - 1) + (front ? -1 : 1) * followingOf(steps, front);
    }

    private void parseInput(List<String> inputList) {
        inputList.stream().forEach(l -> lines.add(Arrays.stream(l.split(" +")).map(s -> Integer.parseInt(s)).toList()));
    }
}