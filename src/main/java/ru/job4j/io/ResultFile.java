package ru.job4j.io;

import javax.imageio.IIOException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 1; i <= 10; i++) {
                outputStream.write(String.valueOf(i).getBytes());
                outputStream.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
