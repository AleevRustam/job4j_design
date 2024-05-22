package ru.job4j.io;

import java.io.*;

public class Analysis {

    public static void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(new FileWriter(target))) {

            boolean serverDone = false;
            String startTime = null;
            String line;
            String[] parts;

            while ((line = input.readLine()) != null) {
                parts = line.split(" ");
                if ((!"200".equals(parts[0]) && !"300".equals(parts[0])) == !serverDone) {
                    output.printf(serverDone ? "%s;%s;%n" : "", startTime, parts[1]);
                    serverDone = !serverDone;
                    startTime = serverDone ? parts[1] : null;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        unavailable("data/server.log", "data/target.csv");
    }
}
