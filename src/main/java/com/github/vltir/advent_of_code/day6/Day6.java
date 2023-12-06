package com.github.vltir.advent_of_code.day6;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day6 implements Day {

    @Override
    public String run(boolean stage2) {
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day6/input.txt");
        List<WhatYouLike> inputRecords;
        if (!stage2) {
            inputRecords = parseInput(inputList);
        } else {
            inputRecords = parseInput2(inputList);
        }
        long counter = 1;
        for (WhatYouLike inputRecord : inputRecords) {
            List<Double> recordTimings = abcFormula(-1, inputRecord.time, -inputRecord.distance);
            Collections.sort(recordTimings);
            double firstBeatingNumberBeforeRounding = recordTimings.get(0);
            double lastBeatingNumberBeforeRounding = recordTimings.get(1);

            long firstBeatingNumber = (long) Math.ceil(recordTimings.get(0));
            long lastBeatingNumber = (long) Math.floor(recordTimings.get(1));

            firstBeatingNumber += firstBeatingNumberBeforeRounding == firstBeatingNumber ? 1 : 0;
            lastBeatingNumber -= lastBeatingNumberBeforeRounding == lastBeatingNumber ? 1 : 0;

            long amountOfBeatingNumers = lastBeatingNumber - firstBeatingNumber + 1;
            counter *= amountOfBeatingNumers;
        }
        return counter + "";
    }

    private List<WhatYouLike> parseInput2(List<String> inputList) {
        List<WhatYouLike> recordList = new ArrayList<>();
        String time = inputList.get(0).split(": *")[1].replace(" ", "");
        String distance = inputList.get(1).split(": *")[1].replace(" ", "");
        recordList.add(new WhatYouLike(Long.parseLong(time), Long.parseLong(distance)));
        return recordList;
    }

    private List<WhatYouLike> parseInput(List<String> inputList) {
        String[] times = inputList.get(0).split(": *")[1].split(" +");
        String[] distances = inputList.get(1).split(": *")[1].split(" +");
        List<WhatYouLike> recordList = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            recordList.add(new WhatYouLike(Long.parseLong(times[i]), Long.parseLong(distances[i])));
        }
        return recordList;
    }

    private List<Double> abcFormula(long a, long b, long c) {
        List<Double> result = new ArrayList<>(2);
        result.add((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));
        result.add((-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a));
        return result;
    }

    private record WhatYouLike(long time, long distance) {
    }
}