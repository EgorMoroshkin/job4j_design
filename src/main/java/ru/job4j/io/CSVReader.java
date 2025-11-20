package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        StringBuilder headerResult = new StringBuilder();

        Scanner scanner = new Scanner(Path.of(path), StandardCharsets.UTF_8);

        String headerLine = scanner.nextLine();
        String[] allColumns = headerLine.split(delimiter);
        String[] neededColumns = filter.split(",");

        List<Integer> columnsIndexes = new ArrayList<>();
        getColumnsIndexes(neededColumns, allColumns, columnsIndexes);

        getHeaderResult(columnsIndexes, allColumns, headerResult, delimiter);

        if ("stdout".equals(out)) {
            System.out.println(headerResult);
            while (scanner.hasNextLine()) {
                System.out.println(getOut(scanner, delimiter, columnsIndexes));
            }

        } else {
            try (BufferedWriter writer = Files.newBufferedWriter(Path.of(out), StandardCharsets.UTF_8)) {
                writer.write(headerResult.toString());
                writer.newLine();
                while (scanner.hasNextLine()) {
                    writer.write(getOut(scanner, delimiter, columnsIndexes).toString());
                    writer.newLine();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        validateParams(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }

    public static void validateParams(String[] str) {
        if (str.length != 3) {
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

    public static void getColumnsIndexes(String[] neededColumns, String[] allColumns, List<Integer> columnsIndexes) {
        for (String needed : neededColumns) {
            for (int i = 0; i < allColumns.length; i++) {
                if (needed.equals(allColumns[i])) {
                    columnsIndexes.add(i);
                    break;
                }
            }
        }
    }

    public static void getHeaderResult(List<Integer> columnsIndexes, String[] allColumns, StringBuilder headerResult,
                                       String delimiter) {
        for (int i = 0; i < columnsIndexes.size(); i++) {
            int colIndex = columnsIndexes.get(i);
            headerResult.append(allColumns[colIndex]);

            if (i < columnsIndexes.size() - 1) {
                headerResult.append(delimiter);
            }
        }
    }

    private static StringBuilder getOut(Scanner scanner, String delimiter, List<Integer> columnsIndexes) {
        String line = scanner.nextLine();
        String[] data = line.split(delimiter);
        StringBuilder dataResult = new StringBuilder();
        for (int i = 0; i < columnsIndexes.size(); i++) {
            int colIndex = columnsIndexes.get(i);
            dataResult.append(data[colIndex]);

            if (i < columnsIndexes.size() - 1) {
                dataResult.append(delimiter);
            }
        }
        return dataResult;
    }
}