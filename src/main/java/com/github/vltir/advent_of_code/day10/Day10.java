package com.github.vltir.advent_of_code.day10;

import com.github.vltir.advent_of_code.Day;
import com.github.vltir.advent_of_code.InputReader;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day10 implements Day {
    private List<List<Pipe>> pipes = new ArrayList<>();

    private Pos startPos;

    @Override
    public String run(boolean stage2) {
        List<String> inputList = InputReader.readInput("./src/main/java/com/github/vltir/advent_of_code/day10/input.txt");
        parseInput(inputList);
        setStartPos();
        int steps = 0;
        Pos pos = startPos;
        Pipe blocked = new Pipe(false, false, false, false);
        while (!startPos.equals(pos) || steps == 0) {
            if (pipes.get(pos.x).get(pos.y).north && !blocked.north) {
                pos = new Pos(pos.x - 1, pos.y);
                blocked = new Pipe(false, true, false, false);
            } else if (pipes.get(pos.x).get(pos.y).south && !blocked.south) {
                pos = new Pos(pos.x + 1, pos.y);
                blocked = new Pipe(true, false, false, false);
            } else if (pipes.get(pos.x).get(pos.y).west && !blocked.west) {
                pos = new Pos(pos.x, pos.y - 1);
                blocked= new Pipe(false,false,false,true);
            } else if (pipes.get(pos.x).get(pos.y).east && !blocked.east) {
                pos = new Pos(pos.x, pos.y + 1);
                blocked = new Pipe(false,false,true,false);
            }
            steps++;
        }
        return steps/2+"";
    }

    private void setStartPos() {
        for (int i = 0; i < pipes.size(); i++) {
            for (int j = 0; j < pipes.get(i).size(); j++) {
                Pipe pipe = pipes.get(i).get(j);
                if (pipe.north && pipe.south && pipe.west && pipe.east) {
                    startPos = new Pos(i, j);
                    boolean north = pipes.get(i - 1).get(j).south;
                    boolean south = pipes.get(i + 1).get(j).north;
                    boolean west = pipes.get(i).get(j - 1).east;
                    boolean east = pipes.get(i).get(j + 1).west;
                    pipes.get(i).set(j, new Pipe(north, south, west, east));
                }
            }
        }
    }

    private void parseInput(List<String> inputList) {
        for (String line : inputList) {
            List<Pipe> row = new ArrayList<>();
            for (char c : line.toCharArray()) {
                row.add(switch (c) {
                    case '|' -> new Pipe(true, true, false, false);
                    case '-' -> new Pipe(false, false, true, true);
                    case 'L' -> new Pipe(true, false, false, true);
                    case 'J' -> new Pipe(true, false, true, false);
                    case '7' -> new Pipe(false, true, true, false);
                    case 'F' -> new Pipe(false, true, false, true);
                    case 'S' -> new Pipe(true, true, true, true);
                    default -> new Pipe(false, false, false, false);
                });
            }
            pipes.add(row);
        }
    }

    public record Pipe(boolean north, boolean south, boolean west, boolean east) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pipe pipe = (Pipe) o;
            return north == pipe.north && south == pipe.south && west == pipe.west && east == pipe.east;
        }

        @Override
        public int hashCode() {
            return Objects.hash(north, south, west, east);
        }
    }

    public record Pos(int x, int y) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
