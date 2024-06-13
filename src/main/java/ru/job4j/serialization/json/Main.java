package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Student student = new Student(true, 3, new Address("Moscow"),
                new String[]{"Math", "Chemistry"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(student));

        final String studentJson =
                "{"
                        + "\"sex\":true,"
                        + "\"level\":3,"
                        + "\"address\":"
                        + "{"
                        + "\"city\":\"Moscow\""
                        + "},"
                        + "\"subjects\":"
                        + "[\"Math\",\"Chemistry\"]"
                        + "}";

        final Student studentMod = gson.fromJson(studentJson, Student.class);
        System.out.println(studentMod);
    }
}
