package com.example.seniorproject1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class platformController {
    @FXML
    private Label confirmMessageLabel;
    @FXML
    private ListView platformListView;

    @FXML
    private ComboBox<String> platformComboBox;
    ObservableList<String> obsList = FXCollections.observableArrayList();

    DatabaseConnection dc = new DatabaseConnection();
    UserProfileModel user = UserProfileModel.getInstance();

    public void initialize() throws SQLException {
        dc.getConnection();
        setUpComboPlatform();
        int temp = dc.getProfID(user.getUserName());
        displayPlatforms(temp);
    }


    private void setUpComboPlatform() {
        platformComboBox.getItems().addAll("PlayStation", "Xbox", "PC", "Nintendo Switch");
    }

    private String getComboPlatform() {
        return platformComboBox.getValue();
    }

    @FXML
    public void switchToProfileOnAction(ActionEvent e) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profilePage.fxml"));
        Scene window = new Scene(fxmlLoader.load(), 1289, 913);
        Main.mainStage.setScene(window);
        Main.mainStage.show();
    }

    @FXML
    public void displayPlatforms(int profileID) throws SQLException {

        ResultSet result = dc.getAllFromFavPlatformByProfileID(profileID);
        //obsList.add("\t\tDescription\t--\t Comments\t--\t User\t--\tGame");
        int i = 1;
        while(result.next()) {
            int platformID = result.getInt("platformID");
            //int id = result.getInt("ProfileID");
            //int game = result.getInt("gameID");
            obsList.add("Platform: " + i + " -- " +  dc.getPlatformName(platformID));
            i++;
        }
        platformListView.setItems(obsList);



    }

    @FXML
    private void addButtonPressOnAction(ActionEvent e) throws SQLException {
        String platformObj = getComboPlatform();
        int profileID = dc.getProfID(user.getUserName());
        int platform = dc.getPlatformIDFromName(platformObj);
        int a = dc.existingPlatInFav(profileID, platform);
        if (dc.existingPlatInFav(profileID, a) < 1) {
            try {
                dc.insertFavoritesPlatform(profileID, platform);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            confirmMessageLabel.setText("Platform Added! Refresh the list to see");
        } else {
            confirmMessageLabel.setText("You already have this platform added!");
        }
    }

    public void refreshList() throws SQLException {
        obsList.clear();
        int temp = dc.getProfID(user.getUserName());
        displayPlatforms(temp);
    }


}




