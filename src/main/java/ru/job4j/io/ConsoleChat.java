package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    private final Random random;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        this.random = new Random();
    }

    public void run() {
        List<String> phrases = readPhrases();
        if (phrases.isEmpty()) {
            System.out.println("Файл с ответами бота пуст или не найден!");
            return;
        }

        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in, "UTF-8");
        boolean isActive = true;
        boolean isResponding = true;

        System.out.println("Чат запущен! Введите ваше сообщение:");

        while (isActive) {
            String userMessage = scanner.nextLine();
            log.add("Пользователь: " + userMessage);

            switch (userMessage.toLowerCase()) {
                case OUT:
                    isActive = false;
                    System.out.println("Чат завершен.");
                    break;
                case STOP:
                    isResponding = false;
                    System.out.println("Бот замолчал...");
                    break;
                case CONTINUE:
                    isResponding = true;
                    System.out.println("Бот снова отвечает!");
                    break;
                default:
                    if (isResponding) {
                        String botResponse = phrases.get(random.nextInt(phrases.size()));
                        System.out.println("Бот: " + botResponse);
                        log.add("Бот: " + botResponse);
                    }
                    break;
            }
        }

        scanner.close();
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(botAnswers), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    phrases.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла с ответами: " + e.getMessage());
        }

        return phrases;
    }

    private void saveLog(List<String> log) {
        // Создаем директорию data, если она не существует
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {

            for (String entry : log) {
                writer.write(entry);
                writer.newLine();
            }
            System.out.println("Лог чата сохранен в: " + path);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении лога: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Создаем пути к файлам в директории data
        String botAnswersPath = "data/bot_answers.txt";
        String logPath = "data/chat_log.txt";

        // Создаем файл с ответами бота, если он не существует
        createBotAnswersFile(botAnswersPath);

        ConsoleChat consoleChat = new ConsoleChat(logPath, botAnswersPath);
        consoleChat.run();
    }

    private static void createBotAnswersFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {

                // Записываем стандартные ответы бота
                String[] defaultAnswers = {
                        "Привет! Как дела?",
                        "Интересно... Расскажи подробнее!",
                        "Я тебя понимаю.",
                        "Это очень интересно!",
                        "Продолжай, я слушаю.",
                        "Хм, никогда об этом не задумывался.",
                        "А что ты сам думаешь по этому поводу?",
                        "Согласен с тобой!",
                        "Может, поговорим о чем-то другом?",
                        "Удивительно!",
                        "Я робот, но стараюсь понять человеческие эмоции.",
                        "Продолжайте, ваши мысли очень ценны.",
                        "Интересная точка зрения!",
                        "Спасибо, что поделился!",
                        "Давайте обсудим это детальнее."
                };

                for (String answer : defaultAnswers) {
                    writer.write(answer);
                    writer.newLine();
                }

                System.out.println("Создан файл с ответами бота: " + filePath);
            } catch (IOException e) {
                System.err.println("Ошибка при создании файла с ответами: " + e.getMessage());
            }
        }
    }
}
