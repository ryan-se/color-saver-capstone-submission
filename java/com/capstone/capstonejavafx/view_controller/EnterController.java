package com.capstone.capstonejavafx.view_controller;

import com.capstone.capstonejavafx.DAO.DBUser;
import com.capstone.capstonejavafx.Main;
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

public class EnterController {

    @FXML
    private Button enterClearButton;

    @FXML
    private Button enterLogInButton;

    @FXML
    private Button enterNewUserButton;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private TextField enterUserNameTextField;

    @FXML
    private Label enterErrorLabel;

    @FXML
    private Button buttonHelp;

    Stage stage;
    Parent scene;

    @FXML
    void onClearButtonClick(ActionEvent event) {
        enterErrorLabel.setText("");
        enterUserNameTextField.setText("");
        enterPasswordField.setText("");
    }

    /**
     * Opens the help screen
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
     * Checks the username and password fields for user information. If successful, the user is logged in and
     * enters the program. If unsuccessful, error messages will help the user fix incorrect information.
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onLogInButtonClick(ActionEvent event) throws IOException {
        String user = enterUserNameTextField.getText();
        String pass = enterPasswordField.getText();

        // check the entered username and password by using the method in DBUser
        if (DBUser.logIn(user, pass)) {
            // if credentials are valid, go to the main screen.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainscreen.fxml"));
            loader.load();
            // keep track of button to know where to switch
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            // .showAndWait will launch the new window, but will not execute anything below it until
            // the current scene returns
            stage.show();
        } else {
            enterErrorLabel.setText("Wrong username or password. Please try again.");
        }
        /*
        String user = enterUserNameTextField.getText();
        String password = enterPasswordField.getText();

        String saveduser = "user";
        String savedpass = "pass";

        if (user == saveduser) {
            enterUserNameTextField.setText("Success");
        } else {
            enterUserNameTextField.setText("");
        } */

    }

    /**
     * Opens the new user screen
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onNewUserButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("newuser.fxml"));
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