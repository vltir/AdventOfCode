package com.github.vltir.advent_of_code;

import com.github.vltir.advent_of_code.day1.Day1;
import com.github.vltir.advent_of_code.day2.Day2;
import com.github.vltir.advent_of_code.day3.Day3;
import com.github.vltir.advent_of_code.day4.Day4;
import com.github.vltir.advent_of_code.day5.Day5;

import java.util.HashMap;
import java.util.Map;

public class AdventOfCode {
    public static void main(String[] args) {
        Day day = new Day5();
        String answer1 = day.run(false);
        String answer2 = day.run(true);
        System.out.println("Stage 1: " + answer1 + "\nStage 2: " + answer2);
    }
}
