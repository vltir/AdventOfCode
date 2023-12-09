package com.github.vltir.advent_of_code.day8;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;
import com.github.vltir.advent_of_code.util.MathUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day8 implements Day {
    private char[] instructions;
    private Map<String, Destination> destinationMap = new HashMap<>();

    private boolean stage2;

    @Override
    public String run(boolean stage2) {
        this.stage2 = stage2;
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day8/input.txt");
        parseInput(inputList);
        if (!stage2) {
            return stage1("AAA") + "";
        } else {
            String[] start = destinationMap.keySet().stream().filter(k -> k.charAt(2) == 'A').toArray(String[]::new);
            long[] iterations = Arrays.stream(start).mapToLong(s -> stage1(s)).toArray();
            return MathUtil.lcm(iterations) + "";
        }
    }

    private int stage1(String start) {
        int iterations = 0;
        while (true) {
            for (int i = 0; i < instructions.length; i++) {
                if (finished(start)) return iterations;
                start = instructions[i] == 'L' ? destinationMap.get(start).left : destinationMap.get(start).right;
                iterations++;
            }
        }
    }

    private boolean finished(String start) {
        if (!stage2) return start.equals("ZZZ");
        return start.charAt(2) == 'Z';

    }


    private void parseInput(List<String> input) {
        instructions = input.get(0).toCharArray();
        input.remove(0);
        //input.stream().map(s->s.replace(" = (",", ").replace(")","")).forEach(System.out::println);
        Set<Node> nodes = input.stream().map(s -> s.replace(" = (", ", ").replace(")", "").split(", ")).map(a -> new Node(a[0], new Destination(a[1], a[2]))).collect(Collectors.toSet());
        nodes.forEach(n -> destinationMap.put(n.name, n.destination));
    }

    private record Destination(String left, String right) {
    }

    private record Node(String name, Destination destination) {
    }


}