package com.example.seniorproject1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;


    public void loginButtonOnAction(ActionEvent e ) {
        loginMessageLabel.setText("You tried to login");

        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            //loginMessageLabel.setText("You tried to login");
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String verifyLogin = "SELECT count(1) From UserInfo WHERE Username = \'" + usernameTextField.getText() + "\' AND UserPass = \'" + passwordPasswordField.getText() + " \'";

        try {
            Statement  statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Welcome");
                } else {
                    loginMessageLabel.setText("Invalid login please try again");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    public void validateLogin1() {
        DataConnector connectNow = new DataConnector();


        String verifyLogin = "SELECT count(1) From UserInfo WHERE Username = '" + usernameTextField.getText() + "' AND UserPass = ' " + passwordPasswordField.getText() + " '";

        try {
            Statement  statement = DataConnector.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Welcome");
                } else {
                    loginMessageLabel.setText("Invalid login please try again");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
}