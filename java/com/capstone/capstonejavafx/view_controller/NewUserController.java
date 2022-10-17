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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NewUserController {

    @FXML
    private Button newUserClearButton;

    @FXML
    private TextField newUserNameTextField;

    @FXML
    private PasswordField newUserPasswordField;

    @FXML
    private PasswordField newUserPasswordField2;

    @FXML
    private Button newUserSignUpButton;

    @FXML
    private Label newUserErrorLabel;

    Stage stage;
    Parent scene;

    /**
     * Clears the text fields quickly
     * @param event button click
     */
    @FXML
    void onClickClear(ActionEvent event) {
        newUserNameTextField.setText("");
        newUserPasswordField.setText("");
        newUserPasswordField2.setText("");
    }

    /**
     * Checks that the user name isn't already used in the database, and enters the new user if
     * all validation checks pass
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onClickSignUp(ActionEvent event) throws IOException {
        String newUserName = newUserNameTextField.getText();
        String newUserPass1 = newUserPasswordField.getText();
        String newUserPass2 = newUserPasswordField2.getText();
        int newUserID = DBUser.getNewUserID();
        User newUser = new User(newUserID, newUserName, newUserPass1);

        ObservableList<User> allUsers = DBUser.getAllUsers();
        boolean userExists = true;
        for (User user : allUsers) {
            if (Objects.equals(newUserName, user.getUserName())) {
                userExists = true;
                break;
            } else {
                userExists = false;
            }
        }

        if(newUserName.equals("")) {
            newUserErrorLabel.setText("Please enter a user name.");
        } else if(userExists) {
            newUserErrorLabel.setText("Username " + newUserName + " already exists");
        } else if(newUserPass1.equals("")) {
            newUserErrorLabel.setText("Please enter a password.");
        } else if(newUserPass1.equals(newUserPass2)) {
            DBUser.addUser(newUser);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("enter.fxml"));
            loader.load();
            // keep track of button to know where to switch
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            // .showAndWait will launch the new window, but will not execute anything below it until
            // the current scene returns
            stage.show();
        } else {
            newUserErrorLabel.setText("Error: passwords do not match.");
        }

    }

    /**
     * returns to the log in screen
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onClickBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("enter.fxml"));
        loader.load();
        // keep track of button to know where to switch
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        // .showAndWait will launch the new window, but will not execute anything below it until
        // the current scene returns
        stage.show();
    }

}
