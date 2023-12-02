package com.github.vltir.advent_of_code.day2;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Day2 implements Day {
    @Override
    public String run(boolean stage2) {
        int red =12;
        int green =13;
        int blue =14;
        int idCounter=0;
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day2/input.txt");
        List<Game> gameList = new ArrayList<>();
        for (String inputLine : inputList) {
            gameList.add(new Game(inputLine));
        }

        for (Game game : gameList) {
            if(game.getRed()>red||game.getGreen()>green||game.getBlue()>blue){
                continue;
            }
            idCounter+=game.id;
        }
        if (!stage2)return idCounter+"";

        List<Integer>powerList= gameList.stream().map(game->game.getRed()*game.getGreen()*game.getBlue()).toList();
        int powerCount =0;
        for (Integer power : powerList) {
            powerCount+=power;
        }
        return powerCount+"";
    }
}
