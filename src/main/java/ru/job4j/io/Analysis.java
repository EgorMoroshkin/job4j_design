package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(source));
             BufferedWriter writer = new BufferedWriter((
                     new FileWriter(target)))
        ) {
            String server = null;
            while (reader.ready()) {
                String line = reader.readLine();
                if (server == null && (line.startsWith("400") || (line.startsWith("500")))) {
                    writer.write(line.split(" ")[1] + ";");
                    server = line;
                } else if (server != null && (!line.startsWith("400") && (!line.startsWith("500")))) {
                    writer.write(line.split(" ")[1]);
                    server = null;
                    writer.write('\n');
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}