package ru.job4j.encoding;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Cодержит логику чата
     */
    public void run() {
        List<String> answers = readPhrases();
        if (answers.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        boolean check = true;
        int i = 0;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        log.add(str);
        while (!OUT.equals(str)) {
            if (STOP.equals(str)) {
                check = false;
            }
            if (CONTINUE.equals(str)) {
                check = true;
            }
            if (check) {
                i = random.nextInt(answers.size());
                System.out.println(answers.get(i));
                log.add(answers.get(i));
            }
            str = scanner.nextLine();
            log.add(str);
        }
        saveLog(log);
    }

    /**
     * Читает фразы из файла
     *
     * @return прочитанную фразу из файла
     */
    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        String str;
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            while ((str = reader.readLine()) != null) {
                rsl.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * Cохраняет лог чата в файл
     *
     * @param log принимает коллекцию строк для записи в файл
     */
    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String line : log) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/interviewResult.txt", "./data/interview.txt");
        consoleChat.run();
    }
}