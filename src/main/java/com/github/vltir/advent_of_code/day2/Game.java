package com.github.vltir.advent_of_code.day2;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public final int id;
    private int red=0;
    private int green=0;
    private int blue=0;

    private List<Round> rounds = new ArrayList<>();

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
                switch (colorType){
                    case "red":
                        red=amount;
                        break;
                    case "green":
                        green=amount;
                        break;
                    case "blue":
                        blue=amount;
                        break;
                }
            }
            this.rounds.add(new Round(red,green,blue));
        }
        setLargestOfEach();

    }
    private void setLargestOfEach(){
        for (Round round : rounds) {
            red = Math.max(red,round.getRed());
            green = Math.max(green,round.getGreen());
            blue = Math.max(blue,round.getBlue());
        }
    }

    private class Round{
        private int red,green,blue;
        private Round(int red, int green, int blue){
            this.red=red;
            this.green=green;
            this.blue=blue;
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
