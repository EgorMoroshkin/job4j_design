package ru.job4j.scanner;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class ScannerExample2 {
    public static void main(String[] args) {
        var data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        var scanner = new Scanner(data).useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
