package com.github.vltir.advent_of_code.day8;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;

import java.util.*;
import java.util.stream.Collectors;

public class Day8 implements Day {
    private char[] instructions;
    private Map<String,Destination> destinationMap = new HashMap<>();
    @Override
    public String run(boolean stage2) {
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day8/input.txt");
        parseInput(inputList);
        String start = "AAA";
        int iterations =0;
        while (true){
            for (int i = 0; i < instructions.length; i++) {
                if(start.equals("ZZZ")) return iterations +"";
                start = instructions[i]=='L'?destinationMap.get(start).left:destinationMap.get(start).right;
                iterations++;
            }
        }
    }



    private record Destination(String left, String right){}

    private record Node(String name, Destination destination){}

    private void parseInput(List<String> input){
        instructions = input.get(0).toCharArray();
        input.remove(0);
        //input.stream().map(s->s.replace(" = (",", ").replace(")","")).forEach(System.out::println);
        Set<Node> nodes = input.stream().map(s->s.replace(" = (",", ").replace(")","").split(", ")).map(a->new Node(a[0],new Destination(a[1],a[2]))).collect(Collectors.toSet());
        nodes.forEach(n->destinationMap.put(n.name,n.destination));
    }
}
