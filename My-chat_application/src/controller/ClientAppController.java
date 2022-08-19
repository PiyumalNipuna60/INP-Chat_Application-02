package controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static controller.LoginFormController.userName;

public class ClientAppController extends Thread {
    public Label txtClientName;
    public TextField txtMsg;
    public VBox vBoxPane1;
    public AnchorPane emojiPane;
    public AnchorPane clientContext;
    Socket socket = null;
    PrintWriter printWriter;
    BufferedReader bufferedReader;

    public void initialize() {
        emojiPane.setVisible(false);
        txtClientName.setText(userName);
        connectSocket();
    }

    private void connectSocket() {
        try {
            socket = new Socket("localhost", 5003);
            System.out.println();
            System.out.println("Connect With Server");
            System.out.println(userName + " Enter the Chat");
            System.out.println("____________________");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sentImageOnMouseClicked(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
    }

    public void sentStickerOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void sent_massageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
    }

//    public void AnotherChatOnAction(ActionEvent actionEvent) throws IOException {
//        Stage stage = new Stage();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
//        stage.show();
//    }

    public void oneEmojiOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji02OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji03OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji04OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji05OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji06OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji07OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji08OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji09OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji10OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji11OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji12OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji13OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji14OnMouseClicked(MouseEvent mouseEvent) {
    }

    public void Emoji15OnMouseClicked(MouseEvent mouseEvent) {
    }
}
