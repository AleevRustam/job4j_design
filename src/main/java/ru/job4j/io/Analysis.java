package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public static void unavailable(String source, String target) {
        List<String> logs = new ArrayList<>();
        boolean notWorked = false;
        StringBuilder result = new StringBuilder();
        
        try (BufferedReader input = new BufferedReader(new FileReader(source))) {
            logs = input.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String status = stringSplit(logs.get(0))[0];
        String time = stringSplit(logs.get(0))[1];
        if (!isWorking(status)) {
            notWorked = true;
            result.append(time).append(";");
        }
        for (int i = 1; i < logs.size(); i++) {
            status = stringSplit(logs.get(i))[0];
            time = stringSplit(logs.get(i))[1];
            if (isWorking(status) && notWorked) {
                result.append(time).append(";").append(System.lineSeparator());
                notWorked = false;
            }
            if (!isWorking(status) && !notWorked) {
                notWorked = true;
                result.append(time).append(";");
            }
        }
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            output.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] stringSplit(String string) {
        return string.split(" ");
    }

    private static boolean isWorking(String string) {
        return string.equals("200") || string.equals("300");
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        unavailable("data/server.log", "data/target.csv");
    }
}
