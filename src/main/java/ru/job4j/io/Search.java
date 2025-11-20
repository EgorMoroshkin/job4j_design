package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        validateParams(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validateParams(String[] str) {
        if (str.length != 2) {
            throw new IllegalArgumentException("Arguments != 2");
        }
        if (!str[1].startsWith(".")) {
            throw new IllegalArgumentException("Second arguments is not correc");
        }
        if (!checkPath(str)) {
            throw new IllegalArgumentException("First arguments is not correct");
        }
    }

    private static boolean checkPath(String[] str) {
        File file = new File(str[0]);
        if (str[0].isEmpty()) {
            throw new IllegalArgumentException("First argement is null");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        return true;
    }
}