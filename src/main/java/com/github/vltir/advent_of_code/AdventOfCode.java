package com.github.vltir.advent_of_code;

import com.github.vltir.advent_of_code.day1.Day1;
import com.github.vltir.advent_of_code.day2.Day2;

public class AdventOfCode {
    public static void main(String[] args) {
        Day day = new Day2();
        String answer = day.run(true);
        System.out.println(answer);
    }
}
