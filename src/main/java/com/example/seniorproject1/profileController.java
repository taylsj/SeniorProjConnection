package com.example.seniorproject1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;




public class profileController {
    @FXML
    private ImageView profileImageView;
    @FXML
    private TextField aboutMeTextField;
    @FXML
    private Label userNameLabel;
    @FXML
    private TextArea aboutMeTextArea;
    @FXML
    private Label aboutMeUpdateLabel;
    private UserProfileModel user = UserProfileModel.getInstance();
    private DatabaseConnection dc = new DatabaseConnection();

/*
    public void initialize(URL url, ResourceBundle rb) {
        File profileFile = new File("Program%20Files%20(x86)/New%20folder/profilestatic.png");
        Image profileImage = new Image(profileFile.toURI().toString());
        profileImageView.setImage(profileImage);

    }
*/
    @FXML
    public void updateAboutMeButtonOnAction(ActionEvent e) {
        DatabaseConnection connectNow = new DatabaseConnection();
        connectNow.getConnection();
        if (aboutMeTextArea.getText() != null) {
            connectNow.updateAboutMe(user.getUserName(), aboutMeTextArea.getText());
            aboutMeUpdateLabel.setText("About Me Successfully Updated!");
            user.setAboutMe(aboutMeTextArea.getText());
        }


    }

    @FXML
    public void initialize() {
        userNameLabel.setText(user.getUserName());
        dc.getConnection();
        String temp = dc.getAboutMe(user.getUserName());
        aboutMeTextArea.setText(temp);
    }


    public void logOut() throws IOException {
        user = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login1.fxml"));
        Scene window = new Scene(fxmlLoader.load(),502,593);
        Main.mainStage.setScene(window);
        Main.mainStage.show();
    }




}