package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, String> files;

    public DuplicatesVisitor() {
        this.files = new HashMap<>();
    }
    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(
                Files.size(file),
                file.getFileName().toString());
        if (!files.containsKey(fileProperty)) {
            files.put(fileProperty, file.toAbsolutePath().toString());
        } else {
            String original = files.get(fileProperty);
            System.out.println("Оригинал: " + original);
            System.out.println("Дубликат: " + file.toAbsolutePath());
            System.out.println("---");
        }
        return CONTINUE;
    }
}