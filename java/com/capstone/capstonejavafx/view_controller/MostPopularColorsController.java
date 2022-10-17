package com.capstone.capstonejavafx.view_controller;

import com.capstone.capstonejavafx.DAO.DBColor;
import com.capstone.capstonejavafx.Main;
import com.capstone.capstonejavafx.model.Color;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class MostPopularColorsController {

    @FXML
    private Button buttonPopularColorsExit;

    @FXML
    private TableColumn<Color, String> popularColorNameCol;

    @FXML
    private TableColumn<Color, Integer> popularTimesUsedCol;

    @FXML
    private TableView<Color> tableViewPopularColors;

    @FXML
    private Label timeStampLabel;

    @FXML
    private TextField colorSearchTextField;

    @FXML
    private Button buttonSearchColors;

    @FXML
    private Button buttonResetSearch;

    Stage stage;
    Scene scene;

    /**
     * Returns the user to the main screen
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onActionButtonPopularColorsExit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainscreen.fxml"));
        loader.load();
        // keep track of button to know where to switch
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        // .showAndWait will launch the new window, but will not execute anything below it until
        // the current scene returns
        stage.show();
    }

    /**
     * Searches the most popular colors by using information from a text field
     * @param event button click
     */
    @FXML
    void onClickButtonSearchColors(ActionEvent event) {

        if (!colorSearchTextField.getText().equals("")) {
            String searchTerm = colorSearchTextField.getText().toLowerCase();

            ObservableList<Color> returnedColors = DBColor.searchColors(searchTerm);
            for (Color color : returnedColors) {
                System.out.println(DBColor.countColor(color));
                color.setTimesUsed(DBColor.countColor(color));
            }
            System.out.println(returnedColors);
            tableViewPopularColors.setItems(returnedColors);
            tableViewPopularColors.getSortOrder().add(popularTimesUsedCol);
        }
    }

    /**
     * Resets the search results and shows all of the information again
     * @param event button click
     */
    @FXML
    void onClickButtonResetSearch(ActionEvent event) {

        ObservableList<Color> uniqueColors = DBColor.getUniqueColors();

        for (Color color : uniqueColors) {
            System.out.println(DBColor.countColor(color));
            color.setTimesUsed(DBColor.countColor(color));
        }

        DateTimeFormatter formatedTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        timeStampLabel.setText("Information current as of " + LocalTime.now().format(formatedTime) + " " + LocalDate.now().format(formattedDate));

        tableViewPopularColors.setItems(uniqueColors);
        tableViewPopularColors.getSortOrder().add(popularTimesUsedCol);
        colorSearchTextField.setText("");
    }


    /**
     * Sets up the screen
     */
    public void initialize() {

        ObservableList<Color> mostPopularColors = FXCollections.observableArrayList();
        ObservableList<Color> uniqueColors = DBColor.getUniqueColors();

        for (Color color : uniqueColors) {
            System.out.println(DBColor.countColor(color));
            color.setTimesUsed(DBColor.countColor(color));
        }

        DateTimeFormatter formatedTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        timeStampLabel.setText("Information current as of " + LocalTime.now().format(formatedTime) + " " + LocalDate.now().format(formattedDate));

        tableViewPopularColors.setItems(uniqueColors);

        popularColorNameCol.setCellValueFactory(
                new PropertyValueFactory<>("colorName")
        );
        popularTimesUsedCol.setCellValueFactory(
                new PropertyValueFactory<>("timesUsed")
        );

        tableViewPopularColors.getSortOrder().add(popularTimesUsedCol);
    }

}