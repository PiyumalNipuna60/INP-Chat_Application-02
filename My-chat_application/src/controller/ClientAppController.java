package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientAppController {
    public Label txtClientName;
    public TextArea txtAreaMsg;
    public TextField txtMsg;
    final int PORT=9005;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    String massage = "", reply = "";

    public void sentImageOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void sentStickerOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void sent_massageOnMouseClicked(MouseEvent mouseEvent) {
    }
}
