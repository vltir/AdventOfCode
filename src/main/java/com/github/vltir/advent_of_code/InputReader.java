package com.github.vltir.advent_of_code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputReader {
    public static List<String> readInput(String path){
        try {
            return Files.readAllLines(Path.of(path));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
