package com.example.seniorproject1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class favoritesController {

    @FXML
    private ListView favListView;
    @FXML
    private Button addButton;
    @FXML
    private Button backButton;
    @FXML
    private Label confirmMessageLabel;
    @FXML
    private ComboBox<String> gameListComboBox;
    ObservableList<String> obsList = FXCollections.observableArrayList();


    DatabaseConnection dc = new DatabaseConnection();
    UserProfileModel user = UserProfileModel.getInstance();


    @FXML
    private void initialize() throws SQLException {
        dc.getConnection();
        setUpComboGame();
        int temp = dc.getProfID(user.getUserName());
        displayGames(temp);
    }

    @FXML
    private void addButtonPressOnAction(ActionEvent e) throws SQLException {
        String gameObj = getComboGame();
        int profileID = dc.getProfID(user.getUserName());
        int game = dc.getGameIDFromName(gameObj);
        int a = dc.existingGameInFav(profileID, game);
        System.out.println(a);
        if(dc.existingGameInFav(profileID, a) < 1 ) {
             try {
            dc.insertFavoritesGame(profileID, game);
             } catch (Exception ex) {
            ex.printStackTrace();
        }
    confirmMessageLabel.setText("Game Added! Refresh the list to see");
    } else {
            confirmMessageLabel.setText("This game is already one of your favorites!");
        }
    }

    private void setUpComboGame() {
        gameListComboBox.getItems().addAll("Fifa 23", "OverWatch", "Grand Theft Auto V", "Mario Kart 8");
    }

    private String getComboGame() {
        return gameListComboBox.getValue();
    }



    @FXML
    public void displayGames(int profileID) throws SQLException {
        ResultSet result = dc.getAllFromFavGameByProfileID(profileID);
        //obsList.add("\t\tDescription\t--\t Comments\t--\t User\t--\tGame");
        int i = 1;
        while(result.next()) {
            int gameID = result.getInt("gameID");
            //int id = result.getInt("ProfileID");
            //int game = result.getInt("gameID");
            obsList.add("Favorite Game: " + i + " -- " +  dc.getGameTitle(gameID));
            i++;
        }
        favListView.setItems(obsList);



    }



    public void switchToProfileOnAction(ActionEvent e) throws  Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("profilePage.fxml"));
        Scene window = new Scene(fxmlLoader.load(),1289,913);
        Main.mainStage.setScene(window);
        Main.mainStage.show();
    }

    public void refreshList() throws SQLException {
        obsList.clear();
        int temp = dc.getProfID(user.getUserName());
        displayGames(temp);
    }


}
