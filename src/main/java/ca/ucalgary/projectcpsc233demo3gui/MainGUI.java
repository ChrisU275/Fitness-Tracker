package ca.ucalgary.projectcpsc233demo3gui;
/**
 * CPSC 233 W24 Project Menu.java
 * @author Christopher Umukoro, Kenneth Kapungu, Chido Chiradza
 * Tutorial:16
 * Date:1st March 2024
 * @email christopher.umukoro@ucalgary.ca, kenneth.kapungu@ucalgary.ca, chido.chiradza@ucalgary.ca
 * @version 1.0
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * MainGUI is the main entry point for a JavaFX application that displays an "Athlete Fitness Tracker" interface.
 * It loads its user interface from an FXML file.
 */
public class MainGUI extends Application {

    /**
     * The start method is the main entry point for all JavaFX applications.
     * In this method, the FXML file for the user interface is loaded, and the primary stage is set up.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages if needed, but they will not be primary stages.
     * @throws IOException If there is an error loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML document
        FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("main.fxml"));

        // Create a new scene with the loaded document, with the specified width and height
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        // Set the title of the window (stage)
        stage.setTitle("Athlete Fitness Tracker!");

        // Set the scene on the stage
        stage.setScene(scene);

        // Display the stage
        stage.show();
    }

    /**
     * Main method to launch the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}