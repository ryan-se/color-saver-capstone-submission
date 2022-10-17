package com.capstone.capstonejavafx.view_controller;

import com.capstone.capstonejavafx.DAO.DBUser;
import com.capstone.capstonejavafx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpController {

    @FXML
    private Button goBackButton;

    @FXML
    private TextArea helpTextArea;

    Stage stage;
    Parent root;

    /**
     * helpText is the string that contains the help information
     */
    private String helpText = "How to use this software\n\n" +
            "Creating a new user:\n" +
            "From the first screen, click the button labeled 'New User'\n" +
            "On the following screen, enter a desired user name (this must be unique)\n" +
            "Enter a password you wish to use\n" +
            "Repeat the password, to ensure it was entered properly\n" +
            "Click 'Sign Up'\n" +
            "You may now log in with your new credentials\n" +
            "You may use the 'Clear' button to reset the form and start over.\n" +
            "You may use the 'Back' button to return to the log-in screen.\n\n" +
            "Saving a new color record:\n" +
            "After logging in, use the form at the top left of the screen to record all details.\n" +
            "Choose a color name from the dropdown box if the color is already inside the system.\n" +
            "Alternatively, you may type the color name in the text field provided.\n" +
            "Enter the sheen level of the paint you are using (i.e. Flat, Matte, Eggshell, Satin, Semi-Gloss)\n" +
            "Select the brand of paint you are using\n" +
            "Enter the area you are painting (i.e. Bedroom, Kitchen, Living Room Trim)\n" +
            "Select the date purchased and the date the room was or will be painted.\n" +
            "A color ID will automatically be generated. Click 'Save' to save the new record or 'Clear' to reset the form and start over.\n\n" +
            "Editing an existing color:\n" +
            "Select a color from your records in the table by clicking a row.\n" +
            "Click 'Edit Selected Color'\n" +
            "The form above will be populated with the current information.\n" +
            "Change any information you wish (The color ID may not be modified)\n" +
            "Click 'Save'\n" +
            "The information displayed in the table is now updated.\n\n" +
            "Deleting an existing color:\n" +
            "Click a record in the table to select it.\n" +
            "Click 'Delete Selected Color'\n" +
            "Confirm the action on the alert that pops up.\n" +
            "The color is now deleted.\n\n" +
            "Changing your password:\n" +
            "At the top left of the main screen, you will see the username you are logged in as.\n" +
            "To the right of that, click 'Change Password'\n" +
            "This will bring up the Change Password screen.\n" +
            "Enter your current password.\n" +
            "Choose a new password, then repeat it in the following box.\n" +
            "Click 'Save'\n" +
            "A confirmation box confirms your password was changed, and you will be returned to the main screen.\n\n" +
            "View Latest Colors:\n" +
            "To view latest colors, click the button on the top right portion of the screen.\n" +
            "This brings up the latest colors table, which will show the most recently saved colors from all users.\n" +
            "You may use this information to get ideas about the latest new colors.\n\n" +
            "View Most Popular Colors:" +
            "To view the most popular colors, click the button on the top right portion of the screen.\n" +
            "This table shows the most popular colors used across all users.\n\n" +
            "See How Many Times A Color Was Used:\n" +
            "From the 'Most Popular Colors' screen, use the search box to enter a color name.\n" +
            "If the color exists in the database, the table will be populated with the color and how many times\n" +
            "users have used this color.\n" +
            "Use the 'Reset' button to view all of the most popular colors again.";

    /**
     * Allows the user to go back to the main screen, or to the log-in screen if the user is not logged in
     * @param event button click
     * @throws IOException
     */
    @FXML
    void onActionGoBackButton(ActionEvent event) throws IOException {
        if (DBUser.LoggedInUser.getUserID() > 0) {
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

    /**
     * initialize the screen
     */
    public void initialize() {
        helpTextArea.setText(helpText);
    }

}
