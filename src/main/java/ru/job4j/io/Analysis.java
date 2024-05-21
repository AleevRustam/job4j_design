package ru.job4j.io;

import java.io.*;

public class Analysis {

    public static void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(new FileWriter(target))) {

            boolean serverDone = false;
            String startTime = null;
            String line;

            while ((line =input.readLine()) != null) {
                if (!line.startsWith("200") && !line.startsWith("300")) {
                    if (!serverDone) {
                        serverDone = true;
                        startTime = line.split(" ")[1];
                    }
                } else {
                    if (serverDone) {
                        serverDone = false;
                        output.printf("%s;%s;%n", startTime, line.split(" ")[1]);
                    }
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
