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

public class platformSearchController {

    @FXML
    private ComboBox<String> platformComboBox;
    @FXML
    private ListView platformListView;

    ArrayList<String> platformList;


    ObservableList<String> obsList = FXCollections.observableArrayList();
    DatabaseConnection dc = new DatabaseConnection();

    public void initialize() {
        dc.getConnection();
        setUpComboPlatform();
    }


    private void setUpComboPlatform(){
        platformList = new ArrayList<>(Arrays.asList("PlayStation", "Xbox", "PC", "Nintendo Switch"));
        platformComboBox.setItems(FXCollections.observableArrayList(platformList));
        platformComboBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        try {
                            filterByPlatform(new_value.intValue() + 1);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        //onRunAPI();
                        //filterListByEquipment(equipmentList.get(new_value.intValue()));
                    }
                });



    }

    public void filterByPlatform(int platformID) throws SQLException {
        ResultSet result = dc.getAllFromFavPlatByPlatform(platformID);
        //int i = 1;
        obsList.clear();
        obsList.add("User: ");
        while(result.next()) {
            int platID = result.getInt("platformID");
            int profID = result.getInt("profileID");
            //int id = result.getInt("ProfileID");
            obsList.add(dc.getProfName(profID));
            //i++;
        }
        platformListView.setItems(obsList);



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
