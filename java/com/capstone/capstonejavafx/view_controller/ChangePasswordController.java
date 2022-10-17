package com.capstone.capstonejavafx.view_controller;

import com.capstone.capstonejavafx.DAO.DBUser;
import com.capstone.capstonejavafx.Main;
import com.capstone.capstonejavafx.model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChangePasswordController {

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private Label editPasswordErrorLabel;

    @FXML
    private Button editUserBackButton;

    @FXML
    private Button editUserClearButton;

    @FXML
    private Button editUserSave;

    @FXML
    private PasswordField newUserPasswordField;

    @FXML
    private PasswordField newUserPasswordField2;

    Stage stage;
    Parent root;

    /**
     * When the user clicks the "Back" button, they will be returned to mainscreen.fxml
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onClickBack(ActionEvent event) throws IOException {
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
     * Allows the user to quickly clear the fields of teh form
     * @param event button click
     */
    @FXML
    void onClickClear(ActionEvent event) {
        currentPasswordField.setText("");
        newUserPasswordField.setText("");
        newUserPasswordField2.setText("");
        editPasswordErrorLabel.setText("");
    }

    /**
     * Validates the user's information from the fields, and creates a new user in the database
     * with that information if the validation checks are successful
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onClickSave(ActionEvent event) throws IOException {
        String currentPassword = currentPasswordField.getText();
        String newUserPass1 = newUserPasswordField.getText();
        String newUserPass2 = newUserPasswordField2.getText();
        int userID = DBUser.LoggedInUser.getUserID();

        if(currentPassword.equals("")) {
            editPasswordErrorLabel.setText("Please enter your current user password.");
        } else if(newUserPass1.equals("")) {
            editPasswordErrorLabel.setText("Please enter a new password.");
        } else if (newUserPass2.equals("")) {
            editPasswordErrorLabel.setText("Please repeat the new password.");
        } else if (!newUserPass1.equals(newUserPass2)) {
            editPasswordErrorLabel.setText("The new passwords do not match.");
        } else if (!DBUser.checkPassword(userID, currentPassword)) {
            editPasswordErrorLabel.setText("The current password is incorrect");
        } else if(newUserPass1.equals(newUserPass2)) {
            DBUser.changeUserPasword(userID, newUserPass1);

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Password for user: " + DBUser.LoggedInUser.getUserName() + " was successfully changed.");
            a.showAndWait();

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainscreen.fxml"));
            loader.load();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            editPasswordErrorLabel.setText("Error: passwords do not match.");
        }
    }

}
