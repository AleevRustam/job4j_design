package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> fileProperties = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        fileProperties.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(file);
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        fileProperties.forEach((fileProperty, paths) -> {
            if (paths.size() > 1) {
                System.out.println(fileProperty.getName() + ", " + fileProperty.getSize() + ".");
                paths.forEach(path -> System.out.println("  -- " + path.toAbsolutePath()));
            }
        });
    }
}
