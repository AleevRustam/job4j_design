package ru.job4j.io;

import java.io.*;

public class Analysis {

    public static void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(new FileWriter(target))) {

            String line;
            boolean notWorked = false;
            String startTime = "";
            String endTime;

            while ((line = input.readLine()) != null) {
                String[] parts = line.split(" ");
                String status = parts[0];
                String time = parts[1];
                if (!isWorking(status) && !notWorked) {
                    notWorked = true;
                    startTime = time;
                } else if (isWorking(status) && notWorked) {
                    endTime = time;
                    output.println(startTime + ";" + endTime + ";");
                    startTime = "";

                    notWorked = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static boolean isWorking(String string) {
        return string.equals("200") || string.equals("300");
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        unavailable("data/server.log", "data/target.csv");
    }
}
