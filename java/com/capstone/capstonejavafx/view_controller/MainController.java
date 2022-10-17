package com.capstone.capstonejavafx.view_controller;


import com.capstone.capstonejavafx.DAO.DBColor;
import com.capstone.capstonejavafx.DAO.DBUser;
import com.capstone.capstonejavafx.Main;
import com.capstone.capstonejavafx.model.Color;
import com.capstone.capstonejavafx.model.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    ObservableList<Color> allColors = DBColor.getAllColors();

    @FXML
    private Button buttonClearEntries;

    @FXML
    private Button buttonSaveEntry;

    @FXML
    private Button buttonViewLatestColors;

    @FXML
    private Button buttonViewPopularColors;

    @FXML
    private TableView<Color> colorTable = new TableView();

    @FXML
    private TableColumn<Color, String> tableColumnArea;

    @FXML
    private TableColumn<Color, String> tableColumnBrand;

    @FXML
    private TableColumn<Color, String> tableColumnColor;

    @FXML
    private TableColumn<Color, Date> tableColumnDatePainted;

    @FXML
    private TableColumn<Color, Date> tableColumnDatePurchasesd;

    @FXML
    private TableColumn<Color, String> tableColumnSheen;

    @FXML
    private TextField textFieldColorID;
    @FXML
    private TextField textFieldArea;

    @FXML
    private TextField textFieldBrand;

    @FXML
    private TextField textFieldColor;

    @FXML
    private TextField textFieldDatePainted;

    @FXML
    private TextField textFieldDatePurchased;

    @FXML
    private TextField textFieldSheen;


    @FXML
    private ComboBox<String> comboBoxBrand;

    @FXML
    private ComboBox<String> comboBoxColorName;

    @FXML
    private Button buttonLogOut;

    @FXML
    private Button buttonLogOutAndExit;

    @FXML
    private DatePicker purchasedDatePicker;

    @FXML
    private DatePicker paintedDatePicker;

    @FXML
    private Label errorLabel;

    @FXML
    private Button buttonChangePassword;

    @FXML
    private Button buttonDeleteSelectedcolor;

    @FXML
    private Button buttonEditSelectedColor;

    @FXML
    private Button buttonHelp;

    @FXML
    private Label labelUserName;

    Stage stage;
    Parent root;

    /**
     * Opens the change password screen
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onActionButtonChangePassword(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("changepassword.fxml"));
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
     * Opens teh help screen
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onActionButtonHelp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("help.fxml"));
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
     * Removes a selected color from the database by choosing it from the table and clicking "Delete Color" button
     * @param event button click
     */
    @FXML
    void onActionDeleteSelectedColor(ActionEvent event) {
        if (colorTable.getSelectionModel().isEmpty()) {
            errorLabel.setText("Please select an item to delete from the table first.");
        } else {
            Color selectedColor = colorTable.getSelectionModel().getSelectedItem();

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Are you sure you wish to delete color: " + selectedColor.getColorName());
            a.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> DBColor.deleteColor(selectedColor.getColorID()));
            //refresh table data
            colorTable.setItems(DBColor.getUserColors(DBUser.LoggedInUser.getUserID()));
            errorLabel.setText("");
        }
    }

    /**
     * Allows the user to edit a selected color. The information is populated in the fields above the table for
     * modification purposes.
     * @param event button click
     */
    @FXML
    void onActionEditSelectedColor(ActionEvent event) {
        if (colorTable.getSelectionModel().isEmpty()) {
            errorLabel.setText("Please select an item to edit from the table first.");
        } else {

            Statement stmt;

            try {
                Color selectedColor = colorTable.getSelectionModel().getSelectedItem();
                LocalDate paintedLocalDate = selectedColor.getColorPaintedDate().toLocalDate();
                LocalDate purchasedLocalDate = selectedColor.getColorPurchasedDate().toLocalDate();
                System.out.println("Selected color brand: " + selectedColor.getColorBrand());
                textFieldColorID.setText(String.valueOf(selectedColor.getColorID()));
                textFieldColor.setText(selectedColor.getColorName());
                comboBoxBrand.setValue(selectedColor.getColorBrand());
                textFieldArea.setText(selectedColor.getColorArea());
                textFieldSheen.setText(selectedColor.getColorSheen());
                paintedDatePicker.setValue(paintedLocalDate);
                purchasedDatePicker.setValue(purchasedLocalDate);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Opens the latest colors screen
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onActionButtonViewLatestColors(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("latestcolors.fxml"));
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
     * Opens the most popular colors screen
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onActionButtonViewPopularColors(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("mostpopularcolors.fxml"));
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
     * Clears the entry or modified color information quickly
     * @param event button click
     */
    @FXML
    void onActionClearEntry(ActionEvent event) {
        textFieldArea.setText("");
        comboBoxBrand.setValue("");
        textFieldColor.setText("");
        purchasedDatePicker.setValue(null);
        paintedDatePicker.setValue(null);
        textFieldSheen.setText("");
        textFieldColorID.setText("");
    }

    /**
     * Saves the new color from the information provided in the fields
     * @param event button click
     */
    @FXML
    void onActionSaveEntry(ActionEvent event) {
        // if nothing is inputted into the colorID field, it is a new color to be saved.
        if (textFieldColorID.getText().equals("")) {
            String name = textFieldColor.getText();
            String brand = comboBoxBrand.getSelectionModel().getSelectedItem();
            String sheen = textFieldSheen.getText();
            String area = textFieldArea.getText();
            Date purchased = Date.valueOf(purchasedDatePicker.getValue());
            Date painted = Date.valueOf(paintedDatePicker.getValue());
            int userID = DBUser.LoggedInUser.getUserID();

            Color colorToSave = new Color(name, brand, sheen, area, purchased, painted, userID);

            DBColor.addColor(colorToSave);

            //refresh table data
            colorTable.setItems(DBColor.getUserColors(DBUser.LoggedInUser.getUserID()));
            // if colorID has value, then it is updating the color
        } else {
            int colorID = Integer.parseInt(textFieldColorID.getText());
            String name = textFieldColor.getText();
            String brand = comboBoxBrand.getSelectionModel().getSelectedItem();
            String sheen = textFieldSheen.getText();
            String area = textFieldArea.getText();
            Date purchased = Date.valueOf(purchasedDatePicker.getValue());
            Date painted = Date.valueOf(paintedDatePicker.getValue());
            int userID = DBUser.LoggedInUser.getUserID();

            Color colorToUpdate = new Color(colorID, name, brand, sheen, area, purchased, painted, userID);

            DBColor.updateColor(colorToUpdate);

            //refresh table data
            colorTable.setItems(DBColor.getUserColors(DBUser.LoggedInUser.getUserID()));
        }
    }


    /**
     * A user may choose an existing color and it puts that information in the text field
     * @param event button click
     */
    @FXML
    void colorNameSelected(ActionEvent event) {
        textFieldColor.setText(comboBoxColorName.getSelectionModel().getSelectedItem());
    }


    /**
     * initilize the screen
     */
    public void initialize() {

        labelUserName.setText(DBUser.LoggedInUser.getUserName());

        // Populate the brands list combo box
        List<String> brandsList = new ArrayList<String>();
        brandsList.add("Behr");
        brandsList.add("Benjamin Moore");
        brandsList.add("Kilz");
        brandsList.add("PPG");
        brandsList.add("Pratt & Lambert");
        brandsList.add("Sherwin Williams");
        ObservableList<String> allBrands = FXCollections.observableList(brandsList);
        comboBoxBrand.setItems(allBrands);
        // End brand combo box

        // Add existing colors to combo box
        // Get all colors from DB
        ObservableList<Color> allColorsDB = DBColor.getAllColors();
        // Make a list for the color names
        List<String> allColorsList = new ArrayList<String>();
        // add color names to list
        for (Color color : allColorsDB) {
            // dont add duplicates
            if (!allColorsList.contains(color.getColorName())) {
                allColorsList.add(color.getColorName());
            }
        }
        // TODO: sort the list of color names alphabetically
        // make an observable list out of the color name list
        ObservableList<String> allColorNames = FXCollections.observableList(allColorsList);
        comboBoxColorName.setItems(allColorNames);

        paintedDatePicker.setValue(LocalDate.now());
        purchasedDatePicker.setValue(LocalDate.now());


        ObservableList<Color> userColors = DBColor.getUserColors(DBUser.LoggedInUser.getUserID());


        colorTable.setItems(userColors);

        tableColumnArea.setCellValueFactory(
                new PropertyValueFactory<>("colorArea")
        );
        tableColumnBrand.setCellValueFactory(
                new PropertyValueFactory<>("colorBrand")
        );
        tableColumnColor.setCellValueFactory(
                new PropertyValueFactory<>("colorName")
        );
        tableColumnDatePainted.setCellValueFactory(
                new PropertyValueFactory<>("colorPaintedDate")
        );
        tableColumnDatePurchasesd.setCellValueFactory(
                new PropertyValueFactory<>("colorPurchasedDate")
        );
        tableColumnSheen.setCellValueFactory(
                new PropertyValueFactory<>("colorSheen")
        );

        System.out.println("Logged in user: " + DBUser.LoggedInUser.getUserName());

    }


    /**
     * Exits the program after prompting the user to confirm
     * @param event button click
     */
    @FXML
    void onActionButtonExit(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure you wish to exit?");
        a.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> Platform.exit());
    }

    /**
     * Allows the user to log out and return to the log-in screen without exiting the program
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onActionButtonLogOut(ActionEvent event) throws IOException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Are you sure you wish to log out of user: " + DBUser.LoggedInUser.getUserName() + "?");
        a.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> {
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("enter.fxml"));
                    DBUser.LoggedInUser = new User();
                    try {
                        loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    // keep track of button to know where to switch
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = loader.getRoot();
                    stage.setScene(new Scene(scene));
                    // .showAndWait will launch the new window, but will not execute anything below it until
                    // the current scene returns
                    stage.show();
                });

    }

}
