package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        StringBuilder headerResult = new StringBuilder();

        //Создаем Scanner для чтения файла
        Scanner scanner = new Scanner(Path.of(path), StandardCharsets.UTF_8);

        //Читаем первую строку заголовки = name;age;last_name;education
        String headerLine = scanner.nextLine();
        String[] allColumns = headerLine.split(delimiter);

        //Разбираем фильтр  = name,age
        String[] neededColumns = filter.split(",");

        //Создаем список индексов нужных столбцов = 0,1
        List<Integer> columnsIndexes = new ArrayList<>();
        for (String needed : neededColumns) {
            for (int i = 0; i < allColumns.length; i++) {
                if (needed.equals(allColumns[i])) {
                    columnsIndexes.add(i);
                    break;
                }
            }
        }

        //Читаем и заголовки
        for (int i = 0; i < columnsIndexes.size(); i++) {
            int colIndex = columnsIndexes.get(i);
            headerResult.append(allColumns[colIndex]);

            if (i < columnsIndexes.size() - 1) {
                headerResult.append(delimiter);
            }
        }

        //Выводим данные в консоль или файл
        if ("stdout".equals(out)) {
            //Выводим в консоль
            System.out.println(headerResult);
            //Выводим данные построчно
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(delimiter);
                StringBuilder dataResult = new StringBuilder();
                for (int i = 0; i < columnsIndexes.size(); i++) {
                    int colIndex = columnsIndexes.get(i);
                    dataResult.append(data[colIndex]);

                    if (i < columnsIndexes.size() - 1) {
                        dataResult.append(delimiter);
                    }
                }
                System.out.println(dataResult);
            }

        } else {
            //Вывод в файл
            try (BufferedWriter writer = Files.newBufferedWriter(Path.of(out), StandardCharsets.UTF_8)) {
                //Записываем заголовки
                writer.write(headerResult.toString());
                writer.newLine();
                //Записываем данные
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] data = line.split(delimiter);
                    StringBuilder dataResult = new StringBuilder();
                    for (int i = 0; i < columnsIndexes.size(); i++) {
                        int colIndex = columnsIndexes.get(i);
                        dataResult.append(data[colIndex]);

                        if (i < columnsIndexes.size() - 1) {
                            dataResult.append(delimiter);
                        }
                    }
                    writer.write(dataResult.toString());
                    writer.newLine();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        validateParams(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }

    public static void validateParams(String[] str) {
        if (str.length != 3) {
            throw new IllegalArgumentException("Arguments != 2");
        }
        if (!str[1].startsWith(".")) {
            throw new IllegalArgumentException("Second arguments is not correc");
        }
        if (!checkPath(str)) {
            throw new IllegalArgumentException("First arguments is not correct");
        }
    }

    private static boolean checkPath(String[] str) {
        File file = new File(str[0]);
        if (str[0].isEmpty()) {
            throw new IllegalArgumentException("First argement is null");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        return true;
    }
}