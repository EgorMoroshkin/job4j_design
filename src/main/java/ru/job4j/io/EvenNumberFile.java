package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EvenNumberFile {
    @SuppressWarnings("checkstyle:NoWhitespaceAfter")
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("data/even.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            int read;
            while ((read = inputStream.read()) != -1) {
                stringBuilder.append((char) read);
            }
            String[] lines = stringBuilder.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line + " - четное");
                } else {
                    System.out.println(line + " - не четное");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
