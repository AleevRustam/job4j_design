package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String firstLine = input.readLine();
                    /*if (firstLine != null && firstLine.contains("msg=Bye")) {
                        server.close();
                    }*/
                    if (firstLine != null && firstLine.contains("msg=")) {
                        String msg = firstLine.substring(firstLine.indexOf("msg=") + 4);
                        msg = msg.split(" ")[0];
                        switch (msg) {
                            case "Hello" -> output.write("Hello".getBytes());
                            case "Exit" -> server.close();
                            default -> output.write("What".getBytes());
                        }
                    }
                    output.flush();
                }
            }
        }
    }
}
