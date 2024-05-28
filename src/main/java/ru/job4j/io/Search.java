package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        isValid(args);
        Path startDirectory = Paths.get(args[0]);
        search(startDirectory, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void isValid(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Need two arguments: start folder and file extension");
        } else {
            Path startDirectory = Paths.get(args[0]);
            if (!Files.exists(startDirectory)
                    || !Files.isDirectory(startDirectory)
                    || !Files.isReadable(startDirectory)) {
                throw new IllegalArgumentException("Error: first argument must be a start folder");
            }
            if (!args[1].matches("\\\\.[A-Za-z0-9]{1,3}")) {
                throw new IllegalArgumentException("Error: second argument must be a file extension");
            }
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
