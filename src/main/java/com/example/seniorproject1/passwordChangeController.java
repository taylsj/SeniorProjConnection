package com.example.seniorproject1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.sql.Statement;

public class passwordChangeController {

    @FXML
    private PasswordField currentPassField;
    @FXML
    private PasswordField newPassField;
    @FXML
    private PasswordField confirmPassField;
    @FXML
    private Button passBackButton;
    @FXML
    private Button updatePassButton;
    @FXML
    private Label confirmMessageLabel;

    private DatabaseConnection dc = new DatabaseConnection();
    private UserProfileModel user = UserProfileModel.getInstance();

    @FXML
    public void initialize() {
        dc.getConnection();
    }
    public void updatePassButtonOnAction(ActionEvent e) {

        if(newPassField.getText().equals("")) {
            confirmMessageLabel.setText("New password cannot be empty!");
            return;
        }

        if(!currentPassField.getText().equals(dc.getCurrentPass(user.getUserName()))) {
            confirmMessageLabel.setText("Your current password is incorrect!");
            return;
        }

        if(newPassField.getText().equals(confirmPassField.getText())) {
            dc.updatePassword(user.getUserName(), newPassField.getText());
            confirmMessageLabel.setText("Password changed successfully!");
        } else {
            confirmMessageLabel.setText("New password does not match!");
        }

    }

    public void switchToProfileOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profilePage.fxml"));
        Scene window = new Scene(fxmlLoader.load(),1289,913);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }

    }
















