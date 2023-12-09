package com.github.vltir.advent_of_code;

import com.github.vltir.advent_of_code.day1.Day1;
import com.github.vltir.advent_of_code.day2.Day2;
import com.github.vltir.advent_of_code.day3.Day3;
import com.github.vltir.advent_of_code.day4.Day4;
import com.github.vltir.advent_of_code.day5.Day5;
import com.github.vltir.advent_of_code.day6.Day6;
import com.github.vltir.advent_of_code.day7.Day7;
import com.github.vltir.advent_of_code.day7.Day7Earth;
import com.github.vltir.advent_of_code.day8.Day8;
import com.github.vltir.advent_of_code.day9.Day9;

import java.util.HashMap;
import java.util.Map;

public class AdventOfCode {
    public static void main(String[] args) {
        Day day = new Day9();
        long initialTime = System.nanoTime();
        String answer1 = day.run(false);
        long stage1Time = System.nanoTime();
        String answer2 = day.run(true);
        long stage2Time = System.nanoTime();
        System.out.println("Stage 1: " + answer1 + " in " + (stage1Time - initialTime) / 1000000.0 + " milliseconds" + "\nStage 2: " + answer2 + " in " + (stage2Time - stage1Time) / 1000000.0 + " milliseconds");
    }
}
