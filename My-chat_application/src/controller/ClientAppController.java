package controller;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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

    public void run() {
        try {
            while (true) {
                String msg = bufferedReader.readLine();
                System.out.println("Massage : " + msg);
                String[] token = msg.split(" ");
                String cmd = token[0];
                System.out.println("Cmd : " + cmd);
                StringBuilder fulMsg = new StringBuilder();
                for (int i = 1; i < token.length; i++) {
                    fulMsg.append(token[i]);
                }
                System.out.println("fulMsg : " + fulMsg);
                System.out.println();
                if (cmd.equalsIgnoreCase(userName + " : ")) {
                    continue;
                } else if (fulMsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        HBox hBox = new HBox();

                        if (fulMsg.toString().endsWith(".png") || fulMsg.toString().endsWith(".jpg") || fulMsg.toString().endsWith(".jpeg") || fulMsg.toString().endsWith(".gif")) {
                            System.out.println(fulMsg);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            Text text = new Text(cmd + " ");
                            text.setStyle("-fx-font-size: 15px");
                            ImageView imageView = new ImageView();
                            Image image = new Image(String.valueOf(fulMsg));
                            imageView.setImage(image);
                            imageView.setFitWidth(75);
                            imageView.setFitHeight(75);
                            TextFlow textFlow = new TextFlow(text, imageView);
                            textFlow.setStyle("-fx-color:rgb(239,242,255);"
                                    + "-fx-background-color: rgb(182,182,182);" +
                                    "-fx-background-radius: 10px");
                            textFlow.setPadding(new Insets(5, 0, 5, 5));

                            hBox.getChildren().add(textFlow);
                            vBoxPane1.getChildren().add(hBox);
                        } else {
                            hBox.setAlignment(Pos.CENTER_LEFT);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            Text text = new Text(msg);
                            text.setStyle("-fx-font-size: 15px");
                            TextFlow textFlow = new TextFlow(text);
                            textFlow.setStyle("-fx-color:rgb(239,242,255);"
                                    + "-fx-background-color: rgb(182,182,182);" +
                                    "-fx-background-radius: 10px");
                            textFlow.setPadding(new Insets(5, 0, 5, 5));
                            text.setFill(Color.color(0, 0, 0));
                            hBox.getChildren().add(textFlow);
                            vBoxPane1.getChildren().add(hBox);

                        }
                    }
                });
            }
            bufferedReader.close();
            printWriter.close();
            socket.close();
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
