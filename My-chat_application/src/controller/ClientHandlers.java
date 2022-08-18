package controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandlers extends Thread{
    private ArrayList<ClientHandlers> clients;
    private Socket socket;
    public BufferedReader in;
    public PrintWriter writer;

    public ClientHandlers(Socket socket,ArrayList<ClientHandlers> clients) {
        this.clients = clients;
        this.socket = socket;
    }
}
