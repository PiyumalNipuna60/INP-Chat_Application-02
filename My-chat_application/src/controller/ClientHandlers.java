package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandlers extends Thread {
    private final ArrayList<ClientHandlers> clients;
    private final Socket socket;
    public BufferedReader in;
    public PrintWriter writer;

    public ClientHandlers(Socket socket, ArrayList<ClientHandlers> clients) throws IOException {
        this.clients = clients;
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            String msg;
            while ((msg = in.readLine()) != null) {
                if (msg.equalsIgnoreCase("Exit")) {
                    break;
                }
                for (ClientHandlers cl : clients) {
                    cl.writer.println(msg);
                    System.out.println(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
