package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {
    public Button btnLogIn;
    public TextField txtUserName;

    public ArrayList<String> Users= new ArrayList<String>();

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        String userName=txtUserName.getText();
        System.out.println(Users);
        if(Users.contains(userName)){
            System.out.println("already");
        }else {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ClientApp.fxml"))));
            stage.show();
        }
        Users.add(userName);
    }
}
