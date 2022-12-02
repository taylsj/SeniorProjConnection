package com.example.seniorproject1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class favoriteGameSearchController {

    @FXML
    private ComboBox<String> gameComboBox;
    @FXML
    private ListView gameListView;

    ArrayList<String> gameList;
    ObservableList<String> obsList = FXCollections.observableArrayList();
    DatabaseConnection dc = new DatabaseConnection();

    public void initialize(){
        dc.getConnection();
        setupComboBox();
    }


    private void setupComboBox(){
        gameList = new ArrayList<>(Arrays.asList("FIFA 23", "OverWatch", "Grand Theft Auto V", "Mario Kart 8"));
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

    public void filterByGame(int gameID) throws SQLException {
        ResultSet result = dc.getAllFromFavGameByGame(gameID);
        //int i = 1;
        obsList.clear();
        obsList.add("User: ");
        while(result.next()) {
        int profID = result.getInt("profileID");
            obsList.add(dc.getProfName(profID));
            //i++;
        }
        gameListView.setItems(obsList);



    }

    public void clearList() throws SQLException, IOException {
        obsList.clear();
    }




    public void switchToSearchFilterOnAction(ActionEvent e) throws  Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("searchFilter.fxml"));
        Scene window = new Scene(fxmlLoader.load(),982,723);
        Main.mainStage.setScene(window);
        Main.mainStage.show();


    }
}
