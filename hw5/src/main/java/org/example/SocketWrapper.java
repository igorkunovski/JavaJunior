package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketWrapper implements AutoCloseable {

    //todo add admin with possibility to kick
    private final long id;
    private final Socket socket;
    private final Scanner input;
    private final PrintWriter output;


    SocketWrapper(long id, Socket socket){
        this.id = id;
        this.socket = socket;
        try {
            this.input = new Scanner(socket.getInputStream());
            this.output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long getId() {
        return id;
    }

    public Socket getSocket() {
        return socket;
    }

    public Scanner getInput() {
        return input;
    }

    public PrintWriter getOutput() {
        return output;
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }

    @Override
    public String toString() {
        return String.format("%s", socket.getInetAddress().toString());
    }
}
