package com.example.seniorproject1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    @FXML
    private Hyperlink signUpLink;

    @FXML
    private Hyperlink loginLink;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField tf_firstname;

    @FXML
    private TextField tf_lastname;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField tf_password;

    @FXML
    private PasswordField tf_password1;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private Label loginMessageLabel1;

    @FXML
    private TextField tf_username;




    public void loginButtonOnAction(ActionEvent e ) throws Exception {
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

    public void signUpLinkOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-up.fxml"));
        Stage window = (Stage) signUpLink.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(),830, 590));

    }



    public void loginLinkOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login1.fxml"));
        Stage window = (Stage) loginLink.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(),830, 590));

    }

    public void signUpButtonOnAction(ActionEvent e) {

        // Check to see if the passwords match
        if(tf_password.getText().equals(tf_password1.getText())) {
            loginMessageLabel1.setText("");
        } else {
            loginMessageLabel1.setText("Password does not match !");
        }



        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = tf_firstname.getText();
        String lastname = tf_lastname.getText();
        String email = tf_email.getText();
        String username = tf_username.getText();
        String password = tf_password.getText();

        String insertFields = "INSERT INTO UserInfo (lastName, firstName, email, userName, userPass) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + email + "','" + username + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            registrationMessageLabel.setText("User registered successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }






        /*
        try {
            Statement  statement = connectDB.createStatement();
            statement (1,tf_username.getText());
            ResultSet queryResult = statement.executeQuery(query);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
         */




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
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logged-in.fxml"));
                    Stage window = (Stage) signUpLink.getScene().getWindow();
                    window.setScene(new Scene(fxmlLoader.load(),830, 590));
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