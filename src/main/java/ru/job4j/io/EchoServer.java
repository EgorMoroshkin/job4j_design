package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        String exit = "Exit";
        String hello = "Hello";
        String what = "What";
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    output.write("\r\n".getBytes());
                    String string = input.readLine();
                    System.out.println(string);
                    if (string.contains(exit)) {
                        System.out.println("Сервер завершил работу");
                        server.close();
                        output.write(exit.getBytes());
                    }
                    if (string.contains(hello)) {
                        output.write(hello.getBytes());
                    }
                    if (string.contains(what)) {
                        output.write(what.getBytes());
                    }
                    output.flush();
                }
            }
        }
    }
}