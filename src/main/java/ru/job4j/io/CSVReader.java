package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        List<Integer> indexes = new ArrayList<>();
        List<String[]> lines = new ArrayList<>();

        List<String> filters = Arrays.asList(filter.split(","));

        try (var scanner = new Scanner(Path.of(path))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine().split(delimiter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*get indexes of columns which we must leave*/
        String[] line = lines.get(0);
        for (int i = 0; i < filters.size(); i++) {
            for (int j = 0; j < line.length; j++) {
                if (line[j].equals(filters.get(i))) {
                    indexes.add(j);
                }
            }
        }

        /* build thr output */
        StringBuilder builder = new StringBuilder();
        for (String[] words : lines) {
            builder.append(getStringJoiner(indexes, words, delimiter)).append(System.lineSeparator());
        }
        print(out, builder);
    }

    private static String getStringJoiner(List<Integer> indexes, String[] words, String delimiter) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (Integer index : indexes) {
            for (int i = 0; i < words.length; i++) {
                if (i == index) {
                    joiner.add(words[i]);
                }
            }
        }
        return joiner.toString();
    }

    public static void print(String out, StringBuilder builder) {
        if ("stdout".equalsIgnoreCase(out)) {
            System.out.println(builder);
        } else {
            try (BufferedWriter output = new BufferedWriter(new FileWriter(out))) {
                output.write(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void validate(ArgsName args) {
        if (!args.get("path").isEmpty() && !Files.exists(Paths.get(args.get("path")))) {
            throw new IllegalArgumentException("Error: Directory does not exist: " + args.get("path"));
        }
        if (args.get("delimiter").isEmpty()) {
            throw new IllegalArgumentException("Error: Delimiter does not exist: " + args.get("delimiter"));
        }
        if (args.get("out").isEmpty()) {
            throw new IllegalArgumentException("Error: Out does not exist: " + args.get("out"));
        }
        if (args.get("filter").isEmpty()) {
            throw new IllegalArgumentException("Error: Filter does not exist: " + args.get("filter"));
        }

    }

    public static void main(String[] args) {
        /* здесь добавьте валидацию принятых параметров*/
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        handle(argsName);

    }
}
