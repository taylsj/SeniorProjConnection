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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


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

    @FXML
    private AnchorPane an_menu;

    @FXML
    private AnchorPane an_message;

    @FXML
    private Button homeButton;

    @FXML
    private Button postButton;

    @FXML
    private Button profileButton;

    @FXML
    private Circle profilePic;

    @FXML
    private Hyperlink signOutLink;

    @FXML
    private Label successMessageLabel;

    @FXML
    private TextField tf_favouriteGame;

    @FXML
    private TextField tf_gameGenre;

    @FXML
    private TextField tf_gameGenre1;

    @FXML
    private Button updateButton;

    @FXML
    private Button chatButton;


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


    public void chatButtonOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chatPage.fxml"));
        Stage window = (Stage) chatButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(),830, 590));

    }


    public void homeButtonOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logged-in.fxml"));
        Stage window = (Stage) homeButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(),830, 590));

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


    public void signOutLinkOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login1.fxml"));
        Stage window = (Stage) signOutLink.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load(),830, 590));
    }





}