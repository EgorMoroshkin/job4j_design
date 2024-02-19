package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
                if ("404".equals(tempLine[tempLine.length - 2])) {
                    rls.add(lines);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rls;
    }

    public void saveTo(String out) {
        var data = filter();
        try (BufferedWriter output = new BufferedWriter((new FileWriter(out)))) {
            for (String str : data) {
                output.write(str);
                output.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}