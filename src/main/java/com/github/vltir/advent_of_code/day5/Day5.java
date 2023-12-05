package com.github.vltir.advent_of_code.day5;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;


public class Day5 implements Day {
    List<List<Mapping>> mappingLists;

    @Override
    public String run(boolean stage2) {
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day5/input.txt");
        mappingLists = parseMappings(inputList);
        if (!stage2) {
            List<Long> seeds;
            seeds = parseSeeds(inputList.get(0));


            System.out.println("parsing done");
            List<Long> soils = seeds.stream().map(l -> map(MappingName.SEED_TO_SOIL_MAP, l)).toList();
            System.out.println("soils done");
            List<Long> fertilizer = soils.stream().map(l -> map(MappingName.SOIL_TO_FERTILIZER_MAP, l)).toList();
//        System.out.println(fertilizer);
            List<Long> water = fertilizer.stream().map(l -> map(MappingName.FERTILIZER_TO_WATER_MAP, l)).toList();
//        System.out.println(water);
            List<Long> light = water.stream().map(l -> map(MappingName.WATER_TO_LIGHT_MAP, l)).toList();
//        System.out.println(light);
            List<Long> temperature = light.stream().map(l -> map(MappingName.LIGHT_TO_TEMPERATURE_MAP, l)).toList();
//        System.out.println(temperature);
            List<Long> humidity = temperature.stream().map(l -> map(MappingName.TEMPERATURE_TO_HUMIDITY_MAP, l)).toList();
//        System.out.println(humidity);
            List<Long> location = humidity.stream().map(l -> map(MappingName.HUMIDITY_TO_LOCATION_MAP, l)).toList();
//        System.out.println(location);
            List<Long> sortedLocations = new ArrayList<>(location);
            Collections.sort(sortedLocations);

            return sortedLocations.get(0) + "";
        }
        List<Range> seeds = parseSeedRanges(inputList.get(0));
        List<Range> soils = mapRange(MappingName.SEED_TO_SOIL_MAP,seeds);
        List<Range> fertilizer = mapRange(MappingName.SEED_TO_SOIL_MAP,soils);
        List<Range> water = mapRange(MappingName.SEED_TO_SOIL_MAP,fertilizer);
        List<Range> light = mapRange(MappingName.SEED_TO_SOIL_MAP,water);
        List<Range> temperature = mapRange(MappingName.SEED_TO_SOIL_MAP,light);
        List<Range> humidity = mapRange(MappingName.SEED_TO_SOIL_MAP,temperature);
        List<Range> location = mapRange(MappingName.SEED_TO_SOIL_MAP,humidity);
        List<Long> upperBoundLocations = location.stream().map(r->r.upperBound).toList();
        List<Long> sortedUpperBoundLocations = new ArrayList<>(upperBoundLocations);
        Collections.sort(sortedUpperBoundLocations);

        return sortedUpperBoundLocations.get(0)+"";
    }

    private List<Range> parseSeedRanges(String inputLine) {
        List<Long> inputList = parseSeeds(inputLine);
        List<Range> seedRanges = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i += 2) {
            seedRanges.add(new Range(inputList.get(i), inputList.get(i) + inputList.get(i + 1) - 1));
        }
        return seedRanges;
    }

    private List<Range> mapRange(MappingName name, List<Range> ranges){
        List<Mapping> mappingList = mappingLists.get(name.index);
        List<Range> newRanges = new ArrayList<>();
        for (Mapping mapping : mappingList) {
            for (Range range : ranges) {
                if(range.upperBound<mapping.sourceStart){
                    continue;
                } else if (range.lowerBound>mapping.sourceStart+mapping.range-1) {
                    continue;
                } else if (range.lowerBound>=mapping.sourceStart&&range.upperBound<=mapping.sourceStart+mapping.range-1) {
                    newRanges.add(new Range(range.lowerBound-mapping.sourceStart+mapping.destinationStart,range.upperBound-mapping.sourceStart+mapping.destinationStart));
                } else if (range.lowerBound<mapping.sourceStart&&range.upperBound>= mapping.sourceStart&&range.upperBound<=mapping.sourceStart+mapping.range-1) {
                    newRanges.add(new Range(mapping.destinationStart,mapping.destinationStart+range.upperBound-mapping.sourceStart));
                } else if (range.upperBound>mapping.sourceStart+mapping.range-1&&range.lowerBound>=mapping.sourceStart&&range.lowerBound<=mapping.sourceStart+mapping.range-1) {
                    newRanges.add(new Range(mapping.destinationStart+range.lowerBound-mapping.sourceStart(),mapping.destinationStart+mapping.range-1));
                } else if (range.lowerBound<mapping.sourceStart&&range.upperBound>mapping.sourceStart+mapping.range-1) {
                    newRanges.add(new Range(mapping.destinationStart,mapping.destinationStart+mapping.range-1));
                }
            }
        }
        return newRanges;
    }

    private long map(MappingName name, long l) {
        List<Mapping> mappingList = mappingLists.get(name.index);
        for (Mapping mapping : mappingList) {
            if (l >= mapping.sourceStart && l < mapping.sourceStart + mapping.range) {
                return mapping.destinationStart + l - mapping.sourceStart;
            }
        }
        return l;
    }

    private List<Long> parseSeeds(String inputLine) {
        return Arrays.stream(inputLine.split(": ")[1].split(" ")).map(Long::parseLong).toList();
    }

    private List<List<Mapping>> parseMappings(List<String> inputList) {
        List<String> seedlessInputList = new ArrayList<>(inputList);
        seedlessInputList.remove(0);
        seedlessInputList.remove(1);
        List<String> sections = Arrays.stream(String.join("\n", seedlessInputList).split("\n\n")).toList();
        List<List<Mapping>> mappingLists = new ArrayList<>();
        for (String section : sections) {
            List<String> mappingStrings = Arrays.stream(section.split("\n")).filter(s -> Pattern.compile("^\\d").matcher(s).find()).toList();
            mappingLists.add(mappingStrings.stream().map(s -> s.split(" ")).map(s -> new Mapping(Long.parseLong(s[0]), Long.parseLong(s[1]), Long.parseLong(s[2]))).toList());
        }
        return mappingLists;
    }

    private record Mapping(long destinationStart, long sourceStart, long range) {
    }

    private record Range(long lowerBound, long upperBound) {
    }

}
