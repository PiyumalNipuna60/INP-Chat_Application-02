package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {
    public Button btnLogIn;
    public TextField txtUserName;
    public static String userName;
    Parent scene;

    public ArrayList<String> Users = new ArrayList();

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        userName = txtUserName.getText();
        System.out.println(Users);
        if (Users.contains(userName)) {
            System.out.println("already");
        } else {
            Stage stage = new Stage();
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("../view/ClientApp.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        Users.add(userName);
    }
}
