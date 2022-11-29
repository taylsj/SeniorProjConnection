package com.example.seniorproject1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class searchFilterController {
    @FXML
    private ComboBox gameComboBox;
    @FXML
    private ComboBox platformComboBox;
    @FXML
    private ListView filterListView;
    @FXML
    private Button resetButton;
    @FXML
    private Button backButton;
    ArrayList<String> platformList;
    ArrayList<String> gameList;
    ObservableList<String> obsList = FXCollections.observableArrayList();
    private DatabaseConnection dc = new DatabaseConnection();
    private UserProfileModel user = UserProfileModel.getInstance();



    @FXML
    public void initialize(){
        dc.getConnection();
        setupComboBox();
    }

    @FXML
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

    @FXML
    public void resetList() throws SQLException {
        obsList.clear();
       //filterListView.setItems(obsList);
        displayAllList();
    }

    @FXML
    public void displayAllList() throws SQLException {
        ResultSet result = dc.getAllFromPost();
        obsList.add("\t\tDescription\t--\t Comments\t--\t User\t--\tGame");
        int i = 1;
        while(result.next()) {
            String comments = result.getString("Comments");
            String description = result.getString("Description");
            int id = result.getInt("ProfileID");
            int game = result.getInt("gameID");
            obsList.add("Post" + i + ":\t " + description + "\t--\t" + comments + "\t--\t" + "By User:  " + dc.getProfName(id) + "\t--\t" + "For game:  " + dc.getGameTitle(game));
            i++;
        }
        filterListView.setItems(obsList);



    }

    public void filterByPlatform(int platformID) throws SQLException {
        ResultSet result = dc.getAllFromPostByPlatform(platformID);
        int i = 1;
        obsList.clear();
        obsList.add("\t\tDescription\t--\t Comments\t--\t User");
        while(result.next()) {
            String comments = result.getString("Comments");
            String description = result.getString("Description");
            int id = result.getInt("ProfileID");
            obsList.add("Post" + i + ":\t " + description + "\t--\t" + comments + "\t--\tBy User:  " + dc.getProfName(id));
            i++;
        }
        filterListView.setItems(obsList);



    }

    public void filterByGame(int gameID) throws SQLException {
        ResultSet result = dc.getAllFromPostByGame(gameID);
        int i = 1;
        obsList.clear();
        obsList.add("\t\tDescription\t--\t Comments\t--\t User");
        while(result.next()) {
            String comments = result.getString("Comments");
            String description = result.getString("Description");
            int id = result.getInt("ProfileID");
            obsList.add("Post" + i + ":\t " + description + "\t--\t" + comments + "\t--\tBy User:  " + dc.getProfName(id));
            i++;
        }
        filterListView.setItems(obsList);



    }

    public void switchToLoggedInOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logged-in.fxml"));
        Scene window = new Scene(fxmlLoader.load(),851, 638);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }

    public void switchToRatingsSearchOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ratingSearch.fxml"));
        Scene window = new Scene(fxmlLoader.load(),981, 733);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }

}
