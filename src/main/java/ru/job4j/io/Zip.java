package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static ArgsName arguments;

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                File file = source.toFile();
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Not all arguments are specified");
        }
        if (!args[2].matches(".*\\.zip$")) {
            throw new IllegalArgumentException("The \".zip\" extension is missing.");
        }
        arguments = ArgsName.of(args);
        Search.validate(new String[]{arguments.get("d"), arguments.get("e")});
    }

    public static void main(String[] args) {
        validate(args);
        ArgsName arguments1 = arguments;
        try {
            List<Path> filesToArchive = Search.search(Path.of(arguments1.get("d")),
                    path -> !path.toFile().getName().endsWith(arguments1.get("e")));
            new Zip().packFiles(filesToArchive, new File(arguments1.get("o")));
            System.out.println("Успех!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

