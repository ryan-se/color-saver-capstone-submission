package com.capstone.capstonejavafx.view_controller;

import com.capstone.capstonejavafx.DAO.DBColor;
import com.capstone.capstonejavafx.DAO.DBUser;
import com.capstone.capstonejavafx.Main;
import com.capstone.capstonejavafx.model.Color;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LatestColorsController {

    @FXML
    private Button buttonLatestColorsExit;

    @FXML
    private TableColumn<Color, String> latestColorsColorNameCol;

    @FXML
    private TableColumn<Color, Date> latestColorsDatePaintedCol;

    @FXML
    private TableView<Color> tableViewLatestColors;

    @FXML
    private Label timeStampLabel;

    Stage stage;
    Scene scene;

    /**
     * Allows the user to go back to the main screen
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onActionButtonLatesColorsExit(ActionEvent event) throws IOException {
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
     * initilize the screen
     */
    public void initialize() {


        ObservableList<Color> allColors = DBColor.getAllColors();

        DateTimeFormatter formatedTime = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        timeStampLabel.setText("Information current as of " + LocalTime.now().format(formatedTime) + " " + LocalDate.now().format(formattedDate));


        tableViewLatestColors.setItems(allColors);

        latestColorsColorNameCol.setCellValueFactory(
                new PropertyValueFactory<>("colorName")
        );
        latestColorsDatePaintedCol.setCellValueFactory(
                new PropertyValueFactory<>("colorPaintedDate")
        );

        tableViewLatestColors.getSortOrder().add(latestColorsDatePaintedCol);
    }
}
