package com.github.vltir.advent_of_code.day1;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 implements Day {
    Map<String,Integer> literals = new HashMap<>();
    @Override
    public String  run(boolean stage2) {
        fillLiterals(stage2);
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day1/input.txt");
        int counter =0;
        for (String s : inputList) {
            counter+=findNumber(s);
        }
        return ""+counter;
    }

    private int findNumber(String line){

        int[]lastNum={-1};
        for (int i = 0; i < line.length(); i++) {
            final int startPos = i;
            literals.forEach((k,v) -> {
                if(line.startsWith(k,startPos)){
                    lastNum[0]=v;
                }
            });

        }

        int[]firstNum={-1};
        for (int i = line.length()-1; i >= 0; i--) {
            final int startPos = i;
            literals.forEach((k,v) -> {
                if(line.startsWith(k,startPos)){
                    firstNum[0]=v;
                }
            });

        }
        return firstNum[0]*10 + lastNum[0];
    }

    private void fillLiterals(boolean stage2){
        literals.put("1",1);
        literals.put("2",2);
        literals.put("3",3);
        literals.put("4",4);
        literals.put("5",5);
        literals.put("6",6);
        literals.put("7",7);
        literals.put("8",8);
        literals.put("9",9);
        if (!stage2) return;
        literals.put("one",1);
        literals.put("two",2);
        literals.put("three",3);
        literals.put("four",4);
        literals.put("five",5);
        literals.put("six",6);
        literals.put("seven",7);
        literals.put("eight",8);
        literals.put("nine",9);
    }
}
