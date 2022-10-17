module com.capstone.capstonejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.capstone.capstonejavafx to javafx.fxml;
    exports com.capstone.capstonejavafx;
    exports com.capstone.capstonejavafx.view_controller;
    exports com.capstone.capstonejavafx.model;
    opens com.capstone.capstonejavafx.view_controller to javafx.fxml;
    opens com.capstone.capstonejavafx.model to javafx.fxml;

}