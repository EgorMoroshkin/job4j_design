package ru.job4j.io;

import java.io.*;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("data/newData.txt"));
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("data/output.txt", true))) {
            outputStream.write(inputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
