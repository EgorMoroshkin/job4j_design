package ru.job4j.regex;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class UsageEncoding {
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            reader.lines()
                    .map(string -> string + System.lineSeparator())
                    .forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void writeDataInFile(String path, List<String> data) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            data.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./data/text.txt";
        UsageEncoding encoding = new UsageEncoding();
        List<String> strings = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );

        writeDataInFile(path, strings);

        String string = encoding.readFile(path);
        System.out.println("Данные из файла: ");
        System.out.println(string);
    }
}


