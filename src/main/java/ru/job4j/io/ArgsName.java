package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();


    public String get(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        } else {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] keyValue = validateAndExtract(arg);
            values.put(keyValue[0], keyValue[1]);
        }
    }

    private String[] validateAndExtract(String arg) {
        String regEx = "^-([a-zA-Z0-9_.-]+)=(.+)$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(arg);

        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2)};
        } else {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not start with a '-' character");
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain an equal sign");
            }
            String[] parts = arg.split("=", 2);
            if (parts[0].length() < 2) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
            }
            if (parts[1].isEmpty()) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
            }
            return parts;
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length > 0) {
            ArgsName names = new ArgsName();
            names.parse(args);
            return names;
        } else {
            throw new IllegalArgumentException("Arguments not passed to program");
        }

    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
