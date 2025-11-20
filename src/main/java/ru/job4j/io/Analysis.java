package ru.job4j.io;

import java.io.*;

public class Analysis {
    @SuppressWarnings("checkstyle:RightCurly")
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
        BufferedWriter output = new BufferedWriter((new FileWriter(target)))) {
            String temp;
            boolean server = true;
            String[] tempTable;
            while ((temp = reader.readLine())  != null) {
                tempTable = temp.split(" ");
                if ((tempTable[0].contains("400") | tempTable[0].contains("500")) & server) {
                    output.write(tempTable[1]);
                    output.write(";");
                    server = false;
                }
                if (tempTable[0].contains("200") | tempTable[0].contains("300") && !server) {
                    server = true;
                    output.write(tempTable[1]);
                    output.write(";");
                    output.write("\n");
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
