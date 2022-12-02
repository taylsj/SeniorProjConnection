package com.example.seniorproject1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class PostPageController {

    private UserProfileModel user = UserProfileModel.getInstance();


    @FXML
    private AnchorPane an_menu;

    @FXML
    private Hyperlink signOutLink;

    @FXML
    private Button switchToProfileButton;

    @FXML
    private Button switchToSearchButton;
    @FXML
    private Button postButton;
    @FXML
    private Button switchToHomeButton;

    @FXML
    private Label userNamePostLabel;

    @FXML
    private TextArea commentsTextArea;

    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private Label confirmMessageLabel;
    //@FXML
   // private ComboBox gameComboBox;
    //@FXML
    //private ComboBox platformComboBox;

  //  ArrayList<String> platformList;
   // ArrayList<String> gameList;
    @FXML
    private ComboBox<String> gameComboBox;
    @FXML
    private ComboBox<String> platformComboBox;
    DatabaseConnection dc = new DatabaseConnection();

    @FXML
    void signOutLinkOnAction(ActionEvent event) throws IOException {
        user = null;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login1.fxml"));
        Scene window = new Scene(fxmlLoader.load(),830,590);
        Main.mainStage.setScene(window);
        Main.mainStage.show();
    }

    @FXML
    void switchToProfileOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profilePage.fxml"));
        Scene window = new Scene(fxmlLoader.load(),1289,913);
        Main.mainStage.setScene(window);
        Main.mainStage.show();

    }

    @FXML
    void switchToSearchOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("searchFilter.fxml"));
        Scene window = new Scene(fxmlLoader.load(),1289,913);
        Main.mainStage.setScene(window);
        Main.mainStage.show();

    }

    @FXML
    public void switchToLoggedInOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logged-in.fxml"));
        Scene window = new Scene(fxmlLoader.load(),851,638);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }


    @FXML
    public void initialize() {
        //userNamePostLabel.setText(user.getUserName());
        dc.getConnection();
        setUpComboGame();
        setUpComboPlatform();
        //dc.getConnection();
        //String temp = dc.getAboutMe(user.getUserName());
        //aboutMeTextArea.setText(temp);
    }




    public void postButtonOnAction(ActionEvent e) {


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        int profileID = connectNow.getProfID(user.getUserName());
        //String comments = commentsTextArea.getText();
        //String subject = descriptionTextArea.getText();
        String platformObj = getComboPlatform();
        String gameObj = getComboGame();
        int platform = dc.getPlatformIDFromName(platformObj);
        int game = dc.getGameIDFromName(gameObj);
        if(platformObj != null && gameObj != null) {
            try {
                dc.insertPost(profileID, game, platform, descriptionTextArea.getText(), commentsTextArea.getText());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            confirmMessageLabel.setText("Post Added Successfully!");
        }else {
            confirmMessageLabel.setText("Please add a game and platform");
        }





    }
    private void setUpComboPlatform() {
        platformComboBox.getItems().addAll("PlayStation", "Xbox", "PC", "Nintendo Switch");
    }

    private String getComboPlatform(){
        return platformComboBox.getValue();
    }

    private void setUpComboGame() {
        gameComboBox.getItems().addAll("Fifa 23", "OverWatch", "Grand Theft Auto V", "Mario Kart 8");
    }

    private String getComboGame() {
        return gameComboBox.getValue();
    }
    /*
    private void setupComboBox(){
        platformList = new ArrayList<>(Arrays.asList("PlayStation", "Xbox", "PC", "Nintendo Switch"));
        gameList = new ArrayList<>(Arrays.asList("FIFA 23", "OverWatch", "Grand Theft Auto V", "Mario Kart 8"));

        platformComboBox.setItems(FXCollections.observableArrayList(platformList));
        platformComboBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        //System.out.println("index = " + new_value.intValue());
                        try {
                            filterByPlatform(new_value.intValue() + 1);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        //onRunAPI();
                        //filterListByEquipment(equipmentList.get(new_value.intValue()));
                    }
                });
        gameComboBox.setItems(FXCollections.observableArrayList(gameList));
        gameComboBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        try {
                            filterByGame(new_value.intValue() + 1);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        //onRunAPI();
                        //filterListByEquipment(equipmentList.get(new_value.intValue()));
                    }
                });



    }
*/



}
