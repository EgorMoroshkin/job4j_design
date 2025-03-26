package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, List<Path>> filePropertyMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty property = new FileProperty(Files.size(file),
                file.getFileName().toString());
        filePropertyMap.computeIfAbsent(property, key -> new ArrayList<>())
                .add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> filePropertyListEntry : filePropertyMap.entrySet()) {
            if (filePropertyListEntry.getValue().size() > 1) {
                System.out.println(String.format("%s %s",
                        filePropertyListEntry.getKey().getName(),
                        filePropertyListEntry.getKey().getSize()
                ));
                for (Path file : filePropertyListEntry.getValue()) {
                    System.out.println(String.format("%s", file.toAbsolutePath()));
                }
            }
        }
    }
}