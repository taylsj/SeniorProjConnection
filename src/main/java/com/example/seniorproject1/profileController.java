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
    private Label userNameLabel;
    @FXML
    private TextArea aboutMeTextArea;
    @FXML
    private Label aboutMeUpdateLabel;
    private UserProfileModel user = UserProfileModel.getInstance();
    private DatabaseConnection dc = new DatabaseConnection();
    @FXML
    private Button changePasswordButton;





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
    public void ratingButtonOnAction(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ratingsPage.fxml"));
        Scene window = new Scene(fxmlLoader.load(),1289,913);
        Main.mainStage.setScene(window);
        Main.mainStage.show();
    }

    @FXML
    public void initialize() {
        userNameLabel.setText(user.getUserName());
        dc.getConnection();
        String temp = dc.getAboutMe(user.getUserName());
        aboutMeTextArea.setText(temp);
    }

    public void signOutLinkOnAction() throws IOException {
        user = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login1.fxml"));
        Scene window = new Scene(fxmlLoader.load(),830,590);
        Main.mainStage.setScene(window);
        Main.mainStage.show();
    }

    public void switchToLoggedInOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logged-in.fxml"));
        Scene window = new Scene(fxmlLoader.load(),851,638);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }

    public void changePasswordButtonOnAction (ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("changePasswordPage.fxml"));
        Scene window = new Scene(fxmlLoader.load(),1289,913);
        Main.mainStage.setScene(window);
        Main.mainStage.show();

    }







}