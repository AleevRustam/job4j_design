package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        Scanner scanner = new Scanner(System.in);
        boolean isBotActive = true;
        boolean isRun = true;

        System.out.println("Чат начался. Введите Ваше сообщение:");

        while (isRun) {
            String userInput = scanner.nextLine();
            log.add("Пользователь: " + userInput);

            switch (userInput.toLowerCase()) {
                case OUT:
                    log.add("Чат завершен.");
                    saveLog(log);
                    isRun = false;
                    break;
                case STOP:
                    System.out.println("Пауза. Для продолжения введите слово \"продолжить\"");
                    isBotActive = false;
                    break;
                case CONTINUE:
                    System.out.println("Продолжаем чат.");
                    isBotActive = true;
                    break;
                default:
                    if (isBotActive) {
                        String botAnswer = getRandomPhrase(phrases);
                        System.out.println(botAnswer);
                        log.add("Бот: " + botAnswer);
                    }
                    break;
            }
        }
        scanner.close();
    }

    private List<String> readPhrases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            return reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.defaultCharset(), true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRandomPhrase(List<String> phrases) {
        int index = (int) (Math.random() * phrases.size());
        return phrases.get(index);
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/chat_log.txt", "data/bot_phrases.txt");
        consoleChat.run();
    }
}
