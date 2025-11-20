package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        try (BufferedReader inputStream = new BufferedReader(new FileReader("data/input.txt"))) {
            inputStream.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
