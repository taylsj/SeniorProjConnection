package com.example.seniorproject1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    @FXML
    private ListView homePostListView;

    private DatabaseConnection dc = new DatabaseConnection();

    ObservableList<String> obsList = FXCollections.observableArrayList();

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
    public void switchToPostOnAction(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("postPage.fxml"));
        Scene window = new Scene(fxmlLoader.load(),804,656);
        Main.mainStage.setScene(window);
        Main.mainStage.show();
    }



    @FXML
    public void initialize() throws SQLException, IOException {
        userNameProfileLabel.setText(user.getUserName());
        dc.getConnection();
        displayAllUserPosts(dc.getProfID(user.getUserName()));
        //String temp = dc.getAboutMe(user.getUserName());
        //aboutMeTextArea.setText(temp);
    }

    public void displayAllUserPosts(int profileID) throws IOException, SQLException {
        ResultSet result = dc.getAllFromPostByUser(profileID);
        int i = 1;
        obsList.clear();
        obsList.add("\t\tDescription\t--\t Comments");
        while(result.next()) {
            String comments = result.getString("Comments");
            String description = result.getString("Description");
            //int id = result.getInt("ProfileID");
            obsList.add("Post" + i + ":\t " + description + "\t--\t" + comments);
            i++;
        }
        homePostListView.setItems(obsList);



    }


}
