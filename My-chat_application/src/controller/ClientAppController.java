package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.net.Socket;

public class ClientAppController {
    public Label txtClientName;
    public TextArea txtAreaMsg;
    public TextField txtMsg;
    final int PORT = 5003;
    public VBox vBoxPane1;
    public AnchorPane emojiPane;
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    String massage = "", reply = "";
    public static String name=LoginFormController.userName;

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
        txtClientName.setText(name);
    }

    public void sentImageOnMouseClicked(MouseEvent mouseEvent) throws IOException, ClassNotFoundException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        File filePath = fileChooser.showOpenDialog(stage);
        ObjectOutputStream dataOutputStreamImg = new ObjectOutputStream(socket.getOutputStream());
        // dataOutputStreamImg.writeUTF();
        txtAreaMsg.appendText("img" + filePath.getPath());
    }

    public void sentStickerOnMouseClicked(MouseEvent mouseEvent) {
//        String jaString = new String("view/assets/");
//        writeOutput(jaString);
//        String inputString = readInput();
//        String displayString = jaString + " " + inputString;
//        txtAreaMsg.appendText(displayString);
//        txtAreaMsg.appendText("Conversion Demo");
//    }
//
//    private void writeOutput(String str) {
//        try {
//            FileOutputStream fos = new FileOutputStream("test.txt");
//            Writer out = new OutputStreamWriter(fos, "UTF8");
//            out.write(str);
//            out.close();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String readInput() {
//        StringBuffer buffer = new StringBuffer();
//        try {
//            FileInputStream fis = new FileInputStream("test.txt");
//            InputStreamReader isr = new InputStreamReader(fis, "UTF8");
//            Reader in = new BufferedReader(isr);
//            int ch;
//            while ((ch = in.read()) > -1) {
//                buffer.append((char)ch);
//            }
//            in.close();
//            return buffer.toString();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    public void sent_massageOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        dataOutputStream.writeUTF(txtMsg.getText());
        reply = txtMsg.getText();
        txtAreaMsg.appendText("\n" + name + " : " + reply);
        dataOutputStream.flush();
    }

    public void AnotherChatOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.show();
    }

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
