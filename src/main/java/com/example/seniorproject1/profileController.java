package com.example.seniorproject1;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;




public class profileController implements  Initializable {
    @FXML
    private ImageView profileImageView;
    @FXML
    private TextField aboutMeTextField;


    public void initialize(URL url, ResourceBundle rb) {
        File profileFile = new File("Program%20Files%20(x86)/New%20folder/profilestatic.png");
        Image profileImage = new Image(profileFile.toURI().toString());
        profileImageView.setImage(profileImage);

    }

    public void updateAboutMeButtonOnAction(ActionEvent e) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String aboutMe = aboutMeTextField.getText();


        //String insertFields = "INSERT INTO UserInfo (lastName, firstName, email, userName, userPass) VALUES ('";
        //String insertValues = firstname + "','" + lastname + "','" + email + "','" + username + "','" + password + "')";
        //String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            //statement.executeUpdate(insertToRegister);

            //registrationMessageLabel.setText("User registered successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause();
        }

    }
}
