package com.example.seniorproject1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Controller  {

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

    @FXML
    private Circle profilePic;

    @FXML
    private Button postButton;

    @FXML
    private Hyperlink signOutLink;

    @FXML
    private Button homeButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button chatButton;

    @FXML
    private Button button_send;

    @FXML
    private TextField tf_message;

    @FXML
    private VBox vbox_messages;

    @FXML
    private Label userNameLabel1;

    @FXML
    private Label aboutMeUpdateLabel;

    @FXML
    private TextArea aboutMeTextArea;


    @FXML
    private Button switchToProfileButton;
    @FXML
    private Button switchToSearchButton;



    private DatabaseConnection dc = new DatabaseConnection();


    public void loginButtonOnAction(ActionEvent e) throws Exception {
        loginMessageLabel.setText("You tried to login");

        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            //loginMessageLabel.setText("You tried to login");
            validateLogin();


        } else {
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void chatButtonOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chatPage.fxml"));
        Stage window = (Stage) chatButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(),830, 590));

    }


    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void signUpLinkOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-up.fxml"));
        Stage window = (Stage) signUpLink.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 830, 590));

    }

    public void signOutLinkOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login1.fxml"));
        Stage window = (Stage) signOutLink.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(),830, 590));
    }

    public void profilePictureCircle (URL url, ResourceBundle rb) {
        profilePic.setStroke(Color.SEAGREEN);
        Image im = new Image("Jimmy.jpg",false);
        profilePic.setFill(new ImagePattern(im));
        profilePic.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
    }



    public void loginLinkOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login1.fxml"));
        Stage window = (Stage) loginLink.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(), 830, 590));

    }

    public void postButtonOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("postPage.fxml"));
        Stage window = (Stage) postButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(),830, 590));

    }

    public void profileButtonOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profilePage.fxml"));
        Stage window = (Stage) profileButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(),830, 590));

    }

    public void homeButtonOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logged-in.fxml"));
        Stage window = (Stage) homeButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(),830, 590));

    }


    @FXML
    public void switchToProfileOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profilePage.fxml"));
        Scene window = new Scene(fxmlLoader.load(),1289,913);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }

    @FXML
    public void switchToSearchOnAction(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("searchFilter.fxml"));
        Scene window = new Scene(fxmlLoader.load(),1289,913);
        Main.mainStage.setScene(window);
        Main.mainStage.show();
    }


    public void signUpButtonOnAction(ActionEvent e) {

        // Check to see if the passwords match
        if (tf_password.getText().equals(tf_password1.getText())) {
            loginMessageLabel1.setText("");
        } else {
            loginMessageLabel1.setText("Password does not match !");
        }

        if (!existingUser(tf_username.getText())) {

            System.out.println("User Added");


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
                int userID = connectNow.getUserID(username);
                //game and platform id always set to 5 since they are empty fields in the database tables
                connectNow.insertProfile(userID, 0, 0, " ", " ", 0, username);
            } catch (Exception ex) {
                ex.printStackTrace();
                ex.getCause();
            }
        } else {
            System.out.println("UserName Already Exists. Enter New UserName");

        }


    }

    public boolean existingUser(String userName) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String tableName = "UserInfo";
        String uName = null, uPswd = null;

        try {
            Statement stmt = connectDB.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE userName = \'" + userName + "\'");

            while (result.next()) {
                uName = result.getString("userName");
                //uPswd = result.getString("User_Password");
            }
        } catch (SQLException except) {
            except.printStackTrace();
        }
        System.out.println("Boolean = " + userName.equals(uName));
        return userName.equals(uName);
    }


    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();


        String verifyLogin = "SELECT count(1) From UserInfo WHERE Username = \'" + usernameTextField.getText() + "\' AND UserPass = \'" + passwordPasswordField.getText() + " \'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    UserProfileModel theUser = UserProfileModel.getInstance();
                    theUser.setUserName(usernameTextField.getText());
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logged-in.fxml"));
                    Stage window = (Stage) signUpLink.getScene().getWindow();
                    window.setScene(new Scene(fxmlLoader.load(), 830, 590));
                } else {
                    loginMessageLabel.setText("Invalid login please try again");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
private void onLogin() throws IOException, NoSuchAlgorithmException {
        String str = instance.returnHashPassword(logi)
}
*/



}



