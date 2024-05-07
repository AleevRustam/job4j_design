package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            List<Integer> numbers = Arrays.stream(lines)
                    .map(Integer::parseInt)
                    .toList();
            for (Integer number : numbers) {
                if (number % 2 == 0) {
                    System.out.println(number + " is even number");
                } else {
                    System.out.println(number + " is odd number");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
