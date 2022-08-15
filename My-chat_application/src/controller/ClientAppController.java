package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientAppController {
    public Label txtClientName;
    public TextArea txtAreaMsg;
    public TextField txtMsg;
    final int PORT=9005;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    String massage = "", reply = "";

    public void initialize() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", PORT);
                txtAreaMsg.appendText("Accept Client..!");
                txtAreaMsg.appendText("\n.............................................\n");

                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());

                while (!massage.equals("Exit")) {
                    massage = dataInputStream.readUTF();
                    txtAreaMsg.appendText("\nServer : " + massage);
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

    public void sent_massageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        dataOutputStream.writeUTF(txtMsg.getText());
        reply = txtMsg.getText();
        txtAreaMsg.appendText("\nClient-01 : " + reply);
        dataOutputStream.flush();
    }
}
