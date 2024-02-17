package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> rls = new ArrayList<>();

        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            String lines;
            while ((lines = input.readLine()) != null) {
                String[] tempLine = lines.split(" ");
                if (tempLine[tempLine.length - 2].equals("404")) {
                    rls.add(lines);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rls;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}