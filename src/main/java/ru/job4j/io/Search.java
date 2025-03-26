package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validate(String[] str) {
        if (str.length != 2) {
            throw new IllegalArgumentException("Number of arguments is not equal to 2");
        }
        if (!checkPath(str)) {
            throw new IllegalArgumentException("First arguments is not correct");
        }
        if (!str[1].startsWith(".")) {
            throw new IllegalArgumentException("Second arguments is not correct");
        }
        if (str.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
    }

    private static boolean checkPath(String[] str) {
        if (str[0].isEmpty()) {
            throw new IllegalArgumentException("First arguments is null");
        }
        String filePath = str[0];
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }
}