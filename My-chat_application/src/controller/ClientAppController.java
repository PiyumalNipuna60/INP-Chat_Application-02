package controller;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static controller.LoginFormController.userName;

public class ClientAppController extends Thread {
    public  Label txtClientName;
    public TextField txtMsg;
    public AnchorPane clientContext;
    public VBox vBoxPane1;
    public AnchorPane emojiPane;
    Socket socket=null;
    PrintWriter printWriter;

    private BufferedReader bufferedReader;

    public void initialize(){
        emojiPane.setVisible(false);
        connectSocket();
        txtClientName.setText(userName);
    }

    private void connectSocket() {
        try {
            socket = new Socket("localhost", 5003);
            System.out.println("Connect With Server");
            System.out.println(userName+" Enter the Chat");
            System.out.println("____________________");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            printWriter = new PrintWriter(socket.getOutputStream(), true);

            this.start();

        } catch (IOException e) {

        }
    }

    String cmd;
    public void run() {
        try {
            while (true) {
                String msg = bufferedReader.readLine();
                System.out.println("Message : " + msg);
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println("cmd : " + cmd);
                StringBuilder fulmsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println("fulmsg : " + fulmsg);
                System.out.println();
                if (cmd.equalsIgnoreCase(userName + ":")) {
                    continue;
                } else if (fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        HBox hBox = new HBox();
                      /*  if (!fulmsg.toString().endsWith(".png") || !fulmsg.toString().endsWith(".jpg") || !fulmsg.toString().endsWith(".jpeg") || !fulmsg.toString().endsWith(".gif")) {

                        }*/

                        if (fulmsg.toString().endsWith(".png") || fulmsg.toString().endsWith(".jpg") || fulmsg.toString().endsWith(".jpeg") || fulmsg.toString().endsWith(".gif")) {
                            System.out.println(fulmsg);
                            hBox.setAlignment(Pos.TOP_LEFT);
                            hBox.setPadding(new Insets(5, 10, 5, 5));
                            Text text = new Text(cmd + " ");
                            text.setStyle("-fx-font-size: 15px");
                            ImageView imageView = new ImageView();
                            Image image = new Image(String.valueOf(fulmsg));
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
                        }else{
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

//                txtAreaMsg.appendText(msg + "\n");
            }

            bufferedReader.close();
            printWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send() {
        String msg = txtMsg.getText();
        printWriter.println(userName + ": " + msg);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5, 5, 5, 10));
        Text text = new Text("Me : "+msg);
        text.setStyle("-fx-font-size: 15px");
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-color:rgb(239,242,255);"
                + "-fx-background-color: rgb(15,125,242);" +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        text.setFill(Color.color(0.934, 0.945, 0.996));
        hBox.getChildren().add(textFlow);
        vBoxPane1.getChildren().add(hBox);
        printWriter.flush();
        txtMsg.setText("");
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }

    public void sentImageOnMouseClicked(MouseEvent mouseEvent) throws MalformedURLException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Image");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            printWriter.println(userName + ": " + file.toURI().toURL());
        }
        if (file != null) {
            System.out.println("File Was Selected");
            URL url = file.toURI().toURL();
            System.out.println(url);
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 10, 5, 5));
            ImageView imageView = new ImageView();
            Image image = new Image(String.valueOf(url));
            imageView.setImage(image);
            imageView.setFitWidth(75);
            imageView.setFitHeight(75);
            VBox vBox = new VBox(imageView);
            vBox.setAlignment(Pos.CENTER_RIGHT);
            vBox.setPadding(new Insets(5, 10, 5, 5));
            vBoxPane1.getChildren().add(vBox);
        }

    }

    public void sent_massageOnMouseClicked(MouseEvent mouseEvent) {
        send();
        emojiPane.setVisible(false);
    }

    public void sentStickerOnMouseClicked(MouseEvent mouseEvent) {
        emojiPane.setVisible(true);
    }

    //  emoji ////////
    public void oneEmojiOnMouseClicked(MouseEvent mouseEvent) { sendEmoji(); }

    public void Emoji02OnMouseClicked(MouseEvent mouseEvent) { sendEmoji(); }

    public void Emoji03OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji04OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji05OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji06OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji07OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji08OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji09OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji10OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji11OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji12OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji13OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji14OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void Emoji15OnMouseClicked(MouseEvent mouseEvent) {
        sendEmoji();
    }

    public void sendEmoji(){
        String msg =("\uD83D\uDE42");
        printWriter.println(userName + ": " + msg);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5, 5, 5, 10));
        Text text = new Text("Me : "+msg);
        text.setStyle("-fx-font-size: 15px");
        TextFlow textFlow = new TextFlow(text);
        textFlow.setStyle("-fx-color:rgb(239,242,255);"
                + "-fx-background-color: rgb(15,125,242);" +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        text.setFill(Color.color(0.934, 0.945, 0.996));
        hBox.getChildren().add(textFlow);
        vBoxPane1.getChildren().add(hBox);
        printWriter.flush();
        txtMsg.setText("");
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }


//        txtAreaMsg.appendText("Me : "+"\uD83D\uDE42");
    }
}
