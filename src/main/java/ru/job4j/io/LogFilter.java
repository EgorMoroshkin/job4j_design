package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> rls = new ArrayList<>();
        try (BufferedReader inputStream = new BufferedReader(new FileReader(file))) {
            String lines;
            while ((lines = inputStream.readLine()) != null) {
                String[] tempLine = lines.split(" ");
                if (tempLine.length > 2 && tempLine[tempLine.length - 2].equals("404")) {
                    rls.add(lines);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rls;
    }

    public void saveTo(String out) {
        List<String> data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            for (int i = 0; i < data.size(); i++) {
                output.println(data.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
//        logFilter.filter().forEach(System.out::println);

    }
}
