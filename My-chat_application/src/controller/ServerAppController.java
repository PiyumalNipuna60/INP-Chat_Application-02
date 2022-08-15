package controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAppController {
    public TextArea txtAreaMsg;
    public TextField txtMsg;
    final int PORT = 9005;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    String massage = "", reply = "";

    public String name=LoginFormController.userName;


    public void initialize() {
        CheckClient();
    }

    private void CheckClient() {

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                txtAreaMsg.appendText("Server Start.!");
                Socket localSocket = serverSocket.accept();
                txtAreaMsg.appendText("\nClient " + name + " Connected..!");
                txtAreaMsg.appendText("\n.............................................\n");

                dataInputStream = new DataInputStream(localSocket.getInputStream());

                while (!massage.equals("Exit")) {
                    massage = dataInputStream.readUTF();
                    txtAreaMsg.appendText("\n" + name + " : " + massage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sentImageOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void sentStickerOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void sent_massageOnMouseClicked(MouseEvent mouseEvent) {
    }
}
