package com.github.vltir.advent_of_code.day2;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final int id;
    private int red=0;
    private int green=0;
    private int blue=0;

    private final List<Round> rounds = new ArrayList<>();

    Game(String inputString){
        id =Integer.parseInt(inputString.split(": ")[0].split(" ")[1]);
        String rounds = inputString.split(": ")[1];
        String[]roundAr = rounds.split("; ");
        for (String round : roundAr) {
            String[]colorAr = round.split(", ");
            int red=0;
            int green=0;
            int blue=0;
            for (String color : colorAr) {
                String colorType = color.split(" ")[1];
                int amount =Integer.parseInt(color.split(" ")[0]);
                switch (colorType) {
                    case "red" -> red = amount;
                    case "green" -> green = amount;
                    case "blue" -> blue = amount;
                }
            }
            this.rounds.add(new Round(red,green,blue));
        }
        setLargestOfEach();

    }
    private void setLargestOfEach(){
        for (Round round : rounds) {
            red = Math.max(red,round.red());
            green = Math.max(green,round.green());
            blue = Math.max(blue,round.blue());
        }
    }

    private record Round(int red, int green, int blue) {
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
