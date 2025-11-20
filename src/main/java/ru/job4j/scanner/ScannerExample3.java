package ru.job4j.scanner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

public class ScannerExample3 {
    public static void main(String[] args) throws Exception {
        var data = "A 1B FF 110";
        var file = File.createTempFile("data", null);
        try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file))) {
            output.write(data.getBytes());
        }
        try (var scanner = new Scanner(file).useRadix(16)) {
            while (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            }
        }
    }
}
