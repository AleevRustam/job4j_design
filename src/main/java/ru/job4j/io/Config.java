package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String configString = toString();
        String[] strings = configString.split(System.lineSeparator());

        List<String> list = Arrays.stream(strings).filter(s -> !s.matches("^(?:\\s*#.*|\\s*)$")).toList();

        boolean isValid = list.stream()
                .allMatch(s -> s.matches("^([^#\\s=]+)=([^#\\s].+)$"));
        if (!isValid) {
            throw new IllegalArgumentException("Неправильный формат строки конфигурации.");
        }

        List<String> collect = Arrays.stream(strings).filter(s -> s.matches("^([^#\\s=]+)=([^#\\s].+)$")).toList();

        collect.forEach(string -> {
            int index = string.indexOf("=");
            String key = string.substring(0, index);
            String value = string.substring(index + 1);
            values.put(key, value);
        });
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
