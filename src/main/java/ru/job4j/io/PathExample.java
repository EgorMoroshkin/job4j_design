package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) throws IOException {
               Path directory = Paths.get("src/main/java/ru/job4j/io/path/paths");
        Files.createDirectories(directory);
        Path path = Path.of("src/main/java/ru/job4j/io/path/paths/path.txt");
        Files.createFile(path);
        Files.move(path, Path.of("src/main/java/ru/job4j/io/path/paths/path.txt"));
        Files.delete(directory);
    }
}
