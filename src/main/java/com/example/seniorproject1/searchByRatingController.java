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

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class searchByRatingController {

    ArrayList<String> gameList;
    @FXML
    private ComboBox gameFilterComboBox;
    @FXML
    private ListView ratingsSearchListView;
    @FXML
    private Button allRatingsButton;

    ObservableList<String> obsList = FXCollections.observableArrayList();
    private DatabaseConnection dc = new DatabaseConnection();
    private UserProfileModel user = UserProfileModel.getInstance();

    @FXML
    private void initialize() {
        setupComboBox();
        dc.getConnection();

    }

    private void setupComboBox(){
        gameList = new ArrayList<>(Arrays.asList("FIFA 23", "OverWatch", "Grand Theft Auto V", "Mario Kart 8"));
        gameFilterComboBox.setItems(FXCollections.observableArrayList(gameList));
        gameFilterComboBox.getSelectionModel().selectedIndexProperty()
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

    public void filterByGame(int gameID) throws SQLException {
        ResultSet result = dc.getAllFromPostByGame(gameID);
        int i = 1;
        obsList.clear();
        obsList.add("\tLeadership -- Communication -- Strategizing -- Concentration -- MultiTasking -- User");
        while(result.next()) {
            int leaderShip = result.getInt("Leadership");
            int communication = result.getInt("Communication");
            int strategizing = result.getInt("Strategizing");
            int concentration = result.getInt("Concentration");
            int multiTasking = result.getInt("MultiTasking");
            int id = result.getInt("ProfileID");
            obsList.add("Post" + i + ":\t " + leaderShip + "\t--\t" + communication + "\t--\t" + strategizing + "\t--\t" + concentration + "\t--\t" +
                    communication + "\t--\t" + "For User:  " + dc.getProfName(id));
            i++;
        }
        ratingsSearchListView.setItems(obsList);



    }

    public void displayAllList() throws IOException, SQLException {
            ResultSet result = dc.getAllFromPost();
            int i = 1;
            obsList.clear();
            obsList.add("\tLeadership -- Communication -- Strategizing -- Concentration -- MultiTasking -- User");
            while(result.next()) {
                int leaderShip = result.getInt("Leadership");
                int communication = result.getInt("Communication");
                int strategizing = result.getInt("Strategizing");
                int concentration = result.getInt("Concentration");
                int multiTasking = result.getInt("MultiTasking");
                int id = result.getInt("ProfileID");
                obsList.add("Post" + i + ":\t " + leaderShip + "\t--\t" + communication + "\t--\t" + strategizing + "\t--\t" + concentration + "\t--\t" +
                        communication + "\t--\t" + "For User:  " + dc.getProfName(id));
                i++;
            }
            ratingsSearchListView.setItems(obsList);



        }

    public void allList() throws SQLException, IOException {
        obsList.clear();
        displayAllList();
    }

    public void clearList() throws SQLException, IOException {
        obsList.clear();
    }


    public void switchToLoggedInOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("logged-in.fxml"));
        Scene window = new Scene(fxmlLoader.load(),851,638);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }

    public void switchToSearchFilterOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("searchFilter.fxml"));
        Scene window = new Scene(fxmlLoader.load(),982,723);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }









}
