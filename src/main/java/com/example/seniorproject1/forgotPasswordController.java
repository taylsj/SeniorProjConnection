package com.example.seniorproject1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;

public class forgotPasswordController {

    @FXML
    private Label confirmMessageLabel;
    @FXML
    private TextArea userNameTextArea;
    @FXML
    private PasswordField newPassField;
    @FXML
    private PasswordField confirmPassField;
    private DatabaseConnection dc = new DatabaseConnection();
    //private UserProfileModel user = UserProfileModel.getInstance();


    @FXML
    public void initialize(){
        dc.getConnection();
    }


    @FXML
    public void switchToLoginOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login1.fxml"));
        Scene window = new Scene(fxmlLoader.load(),851,638);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }


}
