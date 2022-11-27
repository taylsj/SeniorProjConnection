package com.example.seniorproject1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LoggedInPageController {

    private UserProfileModel user = UserProfileModel.getInstance();



    @FXML
    private Hyperlink signOutLink;

    @FXML
    private Button switchToProfileButton;

    @FXML
    private Button switchToSearchButton;

    @FXML
    private Label userNameProfileLabel;

    public void signOutLinkOnAction() throws IOException {
        user = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login1.fxml"));
        Scene window = new Scene(fxmlLoader.load(),830,590);
        Main.mainStage.setScene(window);
        Main.mainStage.show();
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

    @FXML
    public void initialize() {
        userNameProfileLabel.setText(user.getUserName());
        //dc.getConnection();
        //String temp = dc.getAboutMe(user.getUserName());
        //aboutMeTextArea.setText(temp);
    }



}
