module csci205_final_project{
        requires java.base;
        requires java.desktop;
        requires java.sql;
        requires javafx.graphics;
        requires javafx.controls;
        requires javafx.fxml;
    requires org.junit.jupiter.api;
    exports main;
        opens main to javafx.fxml;
        }