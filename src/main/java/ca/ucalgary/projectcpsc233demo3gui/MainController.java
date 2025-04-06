package ca.ucalgary.projectcpsc233demo3gui;

/**
 * CPSC 233 W24 Project Menu.java
 * @author Christopher Umukoro, Kenneth Kapungu, Chido Chiradza
 * Tutorial:16
 * Date:1st March 2024
 * @email christopher.umukoro@ucalgary.ca, kenneth.kapungu@ucalgary.ca, chido.chiradza@ucalgary.ca
 * @version 1.0
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;


public class MainController {
    private static Data data = new Data();
    @FXML
    private MenuItem Close;

    @FXML
    private MenuItem Load;

    @FXML
    private MenuItem Save;
    @FXML
    private Label displayNeededInfo;
    @FXML
    private Label stautsOfProgram;

    @FXML
    private TextField idForAthleteFitnessDetails;

    @FXML
    private TextField idForAthleteInfo;

    @FXML
    private TextField idForBMI;

    @FXML
    private TextField sportToCheck;

    @FXML
    private TextField teamForHours;

    @FXML
    private TextField teamForNetCalories;
    private static final String BMI_format = "%-30s\t%-20s\t%-20s\t%-20s\t%-20s%n";
    private static final String BMI_header = String.format(BMI_format, "NAME", "ID", "WEIGHT", "HEIGHT", "BMI");
    private static String BMI_sep = "";

    static {
        for (int i = 0; i < BMI_header.length(); i++) {
            BMI_sep += "-";
        }
    }
    private static final String athlete_format = "%-30s\t%-8s\t%-20s\t%-20s\t%-4s\t%-4s\t%-40s%n";

    private static final String athlete_header = String.format(athlete_format, "NAME", "ID", "SPORT", "TEAM", "WEIGHT", "HEIGHT", "EMAIL");
    private static String athlete_sep = "";

    static {
        for (int i = 0; i < athlete_header.length(); i++) {
            athlete_sep += "-";
        }
    }
    private static final String InSports_format = "%-30s\t%-20s\t%-30s\t%-30s\t%-20s\t%-20s%n";

    private static final String InSports_header = String.format(InSports_format, "NAME", "TEAM", "CALORIE INTAKE", "CALORIE OUTTAKE", "HOURS OF WORKOUT", "BMI");
    private static String InSports_sep = "";

    static {
        for (int i = 0; i < InSports_header.length(); i++) {
            InSports_sep += "-";
        }
    }
    private static final String fitness_format = "%-30s\t%-10s\t%-20s\t%-20s\t%-20s%n";
    private static final String fitness_header = String.format(fitness_format, "NAME", "CALORIE INTAKE", "CALORIE OUTTAKE", "NET CALORIE", "HOURS OF WORKOUT");
    private static String fitness_sep = "";

    static {
        for (int i = 0; i < fitness_header.length(); i++) {
            fitness_sep += "-";
        }
    }
    private static final String topNetCalories_format = "%-30s\t%-8s%n";


    private static final String topNetCalories_header = String.format(topNetCalories_format, "NAME", "TEAM");
    private static String topNetCalories_sep = "";

    static {
        for (int i = 0; i < topNetCalories_header.length(); i++) {
            topNetCalories_sep += "-";
        }
    }
    private static final String topHoursWorkedOut_format = "%-30s\t%-8s%n";


    private static final String topHoursWorkedOut_header = String.format(topHoursWorkedOut_format, "NAME", "HOURS WORKED OUT");
    private static String topHoursWorkedOut_sep = "";

    static {
        for (int i = 0; i < topHoursWorkedOut_header.length(); i++) {
            topHoursWorkedOut_sep += "-";
        }
    }


    /**
     * Displays about information for the application, including version and author details.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void about(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setPrefSize(500, 600);
        alert.setTitle("About Fitness Tracker Program");
        alert.setHeaderText("Information");
        StringBuilder sb = new StringBuilder();
        sb.append("Author: Christopher Umukoro. Email: christopher.umukoro@ucalgary.ca\n");
        sb.append("Author: Kenneth Kapungu. Email: kenneth.kapungu@ucalgary.ca\n");
        sb.append("Author: Chido Chiradza. Email: chido.chiradza@ucalgary.ca\n");
        sb.append("Version: 3.0\n");
        sb.append("Description: This program is a fitness tracker. It tracks the fitness of individual athletes or team athletes based on the amount of hours they workout and the amount of calories they consume and burn. For team athletes, there are two options which can be used to view the top athletes in the team. These top athletes are based on the net calories and number of hours worked out by the athletes in that team. ");
        alert.setContentText(sb.toString());
        alert.showAndWait();
    }
    /**
     * Closes the application.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void CloseAction(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Initiates the process to load athlete data from a file. Opens a file chooser to select the file,
     * loads the data, and updates the display.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void LoadAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load a file.");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setInitialFileName("data.csv");
        File file = fileChooser.showOpenDialog(new Stage());
        Load(file); // Load data from selected file
        viewAthletes(); // Update view with loaded data
    }

    /**
     * Displays all athletes' information on the UI.
     * Formats and displays the data of all athletes stored in the system.
     */
    public void viewAthletes() {
        StringBuilder sb = new StringBuilder();
        sb.append(athlete_header); // Defined elsewhere in the class
        sb.append(athlete_sep);    // Defined elsewhere in the class
        sb.append("\n");
        for (Athlete athlete : data.getAllAthletes()) {
            if (athlete instanceof TeamAthlete teamAthlete) {
                sb.append(String.format(athlete_format, teamAthlete.getName(), teamAthlete.getId(), teamAthlete.getSport(), teamAthlete.getTeam(), teamAthlete.getWeight(), teamAthlete.getHeight(), teamAthlete.getEmail()));
            } else {
                sb.append(String.format(athlete_format, athlete.getName(), athlete.getId(), athlete.getSport(), "null", athlete.getWeight(), athlete.getHeight(), athlete.getEmail()));
            }
        }
        displayNeededInfo.setText(sb.toString());
    }

    /**
     * Loads athlete data from a specified file and updates the UI with the status of the operation.
     * It checks if the data loading was successful and updates the UI accordingly.
     *
     * @param file The file from which data is to be loaded.
     */
    private void Load(File file) {

        stautsOfProgram.setTextFill(Color.BLACK);
        stautsOfProgram.setText("");

        Data data = FileLoader.load(file);
        if (data == null) {
            stautsOfProgram.setTextFill(Color.RED);
            stautsOfProgram.setText(String.format("Failed to load data from file %s%n", file));
        } else {
            stautsOfProgram.setTextFill(Color.GREEN);
            stautsOfProgram.setText(String.format("Loaded data from file %s%n", file));
            MainController.data = data;
        }
        FileLoader.load(file);
    }
    /**
     * Initiates the file saving process. Opens a file chooser and attempts to save the current athlete data.
     * Updates the UI to show whether the save operation was successful.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void SaveAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save a file.");
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setInitialFileName("data.csv");
        File file = fileChooser.showSaveDialog(new Stage());
        if (FileSaver.save(file, data)) {
            stautsOfProgram.setTextFill(Color.GREEN);
            stautsOfProgram.setText(String.format("Saved to file %s%n", file));
        } else {
            stautsOfProgram.setTextFill(Color.RED);
            stautsOfProgram.setText(String.format("Failed to save to file %s%n", file));
        }
    }
    /**
     * Opens a dialog for adding a new athlete. Handles the UI and data binding for the new athlete form.
     *
     * @param event The event that triggered this action.
     * @throws IOException if there is an issue loading the FXML file for the add athlete form.
     */
    @FXML
    void addNewAthlete(ActionEvent event) {
        try {
            // Make sure the FXMLLoader is pointing to the correct FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(MainGUI.class.getResource("add.fxml"));
            fxmlLoader.setRoot(new VBox()); // Set the root manually
            VBox root = fxmlLoader.load(); // Load the FXML file
            Scene scene = new Scene(root, 800, 600);

            // Retrieve the controller associated with the FXML file
            AddController cont = fxmlLoader.getController();
            cont.setData(data, this);

            // Create a new Stage and set the scene
            Stage stage = new Stage();
            stage.setTitle("Add New Athlete");
            stage.setScene(scene); // Set the scene for the stage
            stage.showAndWait(); // Display the stage and wait for it to be closed
        } catch (IOException e) {
            throw new RuntimeException(e); // Handle IOException
        }
    }
    /**
     * Handles the event triggered by pressing the button to display fitness details of an athlete.
     * It validates the input and displays the athlete's fitness details if the ID is correct.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void AthleteFitnessDetailsButton(ActionEvent event) {
        stautsOfProgram.setTextFill(Color.BLACK);
        stautsOfProgram.setText("");
        try{
            int id = Integer.parseInt(idForAthleteFitnessDetails.getText());
            viewAthleteFitnessDetails(id);
        }catch (NumberFormatException e){
            stautsOfProgram.setTextFill(Color.RED);
            stautsOfProgram.setText("Cannot turn ids into integers");
        }

    }
    /**
     * Displays the fitness details of an athlete identified by their ID.
     * This method is called internally when a valid ID is provided.
     *
     * @param id The ID of the athlete whose fitness details are to be viewed.
     */
    private void viewAthleteFitnessDetails(int id) {
        // Check if the athlete with the entered ID exists in the data
        if (data.checkExistAthlete(id)) {
            //print the format
            StringBuilder sb = new StringBuilder();
            sb.append(fitness_header);
            sb.append(fitness_sep);
            sb.append("\n");
            //loop through all athletes in the data
            for (Athlete athlete : data.getAllAthletes()) {
                // Check if the athlete ID matches the entered ID
                if (athlete.getId() == id) {
                    // If the ID matches, print the athlete's fitness details
                    sb.append(String.format(fitness_format, athlete.getName(), athlete.getCalorieIntake()+"\t", "\t"+"\t"+athlete.getCalorieOuttake()+"\t", "\t"+"\t"+athlete.getNetCalorie()+"\t", "\t"+athlete.getHoursOfWorkout()));
                }
            }
            displayNeededInfo.setText(sb.toString());
        } else {
            //if the athlete does not exist, print message
            stautsOfProgram.setTextFill(Color.RED);
            stautsOfProgram.setText("Athlete does not exist.");
        }
    }
    /**
     * Handles the button press to fetch and display athlete information based on the ID entered.
     * Validates the ID and displays the corresponding athlete information or an error if the ID is invalid.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void AthleteInfoButton(ActionEvent event) {
        stautsOfProgram.setTextFill(Color.BLACK);
        stautsOfProgram.setText("");
        try{
            int id = Integer.parseInt(idForAthleteInfo.getText());
            viewAthleteInfo(id);
        }catch (NumberFormatException e){
            stautsOfProgram.setTextFill(Color.RED);
            stautsOfProgram.setText("Cannot turn ids into integers");
        }

    }
    /**
     * Displays the detailed information of a specific athlete based on their ID.
     * The information is formatted and shown in the GUI if the athlete exists.
     *
     * @param id The ID of the athlete whose information is to be displayed.
     */
    private void viewAthleteInfo(int id) {

        if (data.checkExistAthlete(id)) {
            //prints the format
            StringBuilder sb = new StringBuilder();
            sb.append(athlete_header);
            sb.append(athlete_sep);
            sb.append("\n");
            // loop through all athletes in the data
            for (Athlete athlete : data.getAllAthletes()) {
                // Check if the athlete ID matches the entered ID
                if (athlete.getId() == id) {
                    // If the ID matches, print the athlete's details
                    if (athlete instanceof TeamAthlete teamAthlete) {
                        sb.append(String.format(athlete_format, teamAthlete.getName(), teamAthlete.getId(), teamAthlete.getSport(), teamAthlete.getTeam(), teamAthlete.getWeight(), teamAthlete.getHeight(), teamAthlete.getEmail()));
                    }else{
                        sb.append(String.format(athlete_format, athlete.getName(), athlete.getId(), athlete.getSport(), "null", athlete.getWeight(), athlete.getHeight(), athlete.getEmail()));
                    }
                }
            }
            displayNeededInfo.setText(sb.toString());
        } else {
            //if the athlete does not exist, print message
            stautsOfProgram.setTextFill(Color.RED);
            stautsOfProgram.setText("Athlete does not exist.");
        }
    }
    /**
     * Handles the button press to view the BMI of an athlete based on the ID entered.
     * Validates the ID and displays the BMI or an error if the ID is invalid.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void BMIbutton(ActionEvent event) {
        stautsOfProgram.setTextFill(Color.BLACK);
        stautsOfProgram.setText("");
        try{
            int id = Integer.parseInt(idForBMI.getText());
            ViewAthleteBMI(id);
        }catch (NumberFormatException e){
            stautsOfProgram.setTextFill(Color.RED);
            stautsOfProgram.setText("Cannot turn ids into integers");
        }


    }
    /**
     * Displays the BMI information for a specified athlete.
     * The BMI details are formatted and shown in the GUI if the athlete exists.
     *
     * @param id The ID of the athlete whose BMI information is to be displayed.
     */
    private void ViewAthleteBMI(int id) {
        if (data.checkExistAthlete(id)) {
            //prints the format
            StringBuilder sb = new StringBuilder();
            sb.append(BMI_header);
            sb.append(BMI_sep);
            sb.append("\n");
            // loop through all athletes in the data
            for (Athlete athlete : data.getAllAthletes()) {
                //Check if the athlete ID matches the entered ID
                if ((int) athlete.getId() == id) {
                    // If the ID matches, print the athlete's details
                    sb.append(String.format(BMI_format, athlete.getName(), athlete.getId(), athlete.getWeight(), athlete.getHeight(), athlete.getBmi()));
                }
            }
            displayNeededInfo.setText(sb.toString());
        } else {
            //if the athlete does not exist, print message
            stautsOfProgram.setTextFill(Color.RED);
            stautsOfProgram.setText("Athlete with that id does not exist.");

        }
    }
    /**
     * Handles the button press to view athletes participating in a specific sport.
     * Validates the sport name and displays the corresponding athletes or an error if the sport does not exist.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void athletesInSportButton(ActionEvent event) {
        stautsOfProgram.setTextFill(Color.BLACK);
        stautsOfProgram.setText("");
        try{
            String sport= sportToCheck.getText();
            viewAthletesInSports(sport);
        }catch (NumberFormatException e){
            stautsOfProgram.setTextFill(Color.RED);
            stautsOfProgram.setText("Cannot turn ids into integers");
        }
    }
    /**
     * Displays all athletes who participate in a specific sport.
     * It checks for the existence of the sport in the database and updates the GUI with the list of athletes involved in that sport.
     *
     * @param sport The name of the sport to filter athletes by.
     */
    private void viewAthletesInSports(String sport) {
        //checks to see if that sport exists in the stored data
        if (data.checkExistSport(sport)) {
            //prints the format
            StringBuilder sb = new StringBuilder();
            sb.append(InSports_header);
            sb.append(InSports_sep);
            sb.append("\n");
            //goes through each athlete in athletes
            for (Athlete athlete : data.getAllAthletes()) {
                //checks if their sport is equal to the sport entered by the user
                if (athlete.getSport().equals(sport)) {
                    //prints that athlete's details
                    if (athlete instanceof TeamAthlete teamAthlete) {
                        sb.append(String.format(InSports_format, teamAthlete.getName(), teamAthlete.getTeam(), "\t"+teamAthlete.getCalorieIntake()+"\t", "\t"+"\t"+teamAthlete.getCalorieOuttake()+"\t", "\t"+"\t"+teamAthlete.getHoursOfWorkout()+"\t", "\t"+"\t"+"\t"+teamAthlete.getBmi()));
                    }else{
                        sb.append(String.format(InSports_format, athlete.getName(),"null", athlete.getCalorieIntake(), athlete.getCalorieOuttake(), athlete.getHoursOfWorkout(), athlete.getBmi()));
                    }
                }
            }
            displayNeededInfo.setText(sb.toString());
        }
        //prints out this message if the sport not found
        else {
            stautsOfProgram.setTextFill(Color.RED);
            stautsOfProgram.setText("Sport does not exist in this database.");
        }
    }
    /**
     * Displays all registered athletes in the database.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void displayAllAthletes(ActionEvent event) {
        viewAthletes();
    }
    /**
     * Displays all sports available in the database.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void displayAllSports(ActionEvent event) {
        HashSet<String> allSports = new HashSet<>();
        allSports = data.getAllSports();
        // loop through each unique sport and print it
        StringBuilder sb = new StringBuilder();

        for (String allSport : allSports) {
            sb.append(allSport+"\n");
        }
        displayNeededInfo.setText(sb.toString());
    }
    /**
     * Displays the top athletes based on the number of hours worked out, for a specific team.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void topAthletesHoursButton(ActionEvent event) {
        ArrayList<Athlete> newAthleteList = new ArrayList<>();
        String team = teamForHours.getText();
        //checks if the team exists
        if (data.checkExistTeam(team)) {
            //calls the getTeamList function from Data to get a list of IDs of athletes in that team
            ArrayList<Integer> teamIDList = data.getTeamList(team);
            //Initiating a HashMap that would be used to store the ID of an athlete and the athlete's corresponding hours worked out.
            HashMap<Integer, Integer> mapOfIdAndHoursWorkedOut = new HashMap<>();


            // going through each athlete in athletes
            for (Athlete athlete : data.getAllAthletes()) {
                // going through each ID in teamIDList and checking if it matches with the iD of the current athlete in the athletes list
                for (Integer i : teamIDList) {
                    //if it does then we're adding the ID of that athlete as a key and the hours worked out of the athlete as the value.
                    if (athlete instanceof TeamAthlete teamAthlete) {
                        if (teamAthlete.getId() == (i)) {
                            mapOfIdAndHoursWorkedOut.put(i, teamAthlete.getHoursOfWorkout());
                        }
                    }
                }
            }

            List<Map.Entry<Integer, Integer>> sortedEntries = mapOfIdAndHoursWorkedOut.entrySet()
                    .stream()
                    .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                    .collect(toList());


            // Output the top athletes based on hours worked out
            // prints the format
            StringBuilder sb = new StringBuilder();
            sb.append(topHoursWorkedOut_header);
            sb.append(topHoursWorkedOut_sep+"\n");
            //going through each entry in sortedEntries
            for (Map.Entry<Integer, Integer> entry : sortedEntries) {
                // storing the key which is the ID
                Integer athleteId = entry.getKey();
                //going through each athlete in athletes
                for (Athlete athlete : data.getAllAthletes()) {
                    //checking if this athlete's ID is the same as the key which was stored in athleteId
                    if (athlete.getId() == (athleteId)) {
                        //print the details of that athlete
                        if (athlete instanceof TeamAthlete teamAthlete)
                            sb.append(String.format(topHoursWorkedOut_format, teamAthlete.getName(), teamAthlete.getHoursOfWorkout()));
                        break;// No need to continue searching once found
                    }
                }
            }
            displayNeededInfo.setText(sb.toString());
        } else {
            //prints if the team not found.3
            stautsOfProgram.setText("Team does not exist.");
        }
    }
    /**
     * Displays the top athletes based on net calories for a specific team.
     *
     * @param event The event that triggered this action.
     */
    @FXML
    void topAthletesNetButton(ActionEvent event) {
        String team = teamForNetCalories.getText();
        //checks if the team exists
        if (data.checkExistTeam(team)) {
            //calls the getTeamList function from Data to get a list of IDs of athletes in that team
            ArrayList<Integer> teamIDList = data.getTeamList(team);
            //Initiating a HashMap that would be used to store the ID of an athlete and the athlete's corresponding net calorie.
            HashMap<Integer, Double> mapOfIdAndNetCalories = new HashMap<>();

            // going through each athlete in athletes
            for (Athlete athlete : data.getAllAthletes()) {
                // going through each ID in teamIDList and checking if it matches with the iD of the current athlete in the athletes list
                for (Integer i : teamIDList) {
                    //if it does then we're adding the ID of that athlete as a key and the net calorie of the athlete as the value.
                    if (athlete.getId() == (i)) {
                        if (athlete instanceof TeamAthlete teamAthlete) {
                            mapOfIdAndNetCalories.put(i, teamAthlete.getNetCalorie());
                        }
                    }
                }
            }
            // Got this piece of code the next four lines from ChatGPT
            //This piece of code rearranges the HashMap in descending order so the athletes with the top net calories come first.
            List<Map.Entry<Integer, Double>> sortedEntries = mapOfIdAndNetCalories.entrySet()
                    .stream()
                    .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
                    .toList();

            // Output the top athletes based on net calories
            //prints the format
            StringBuilder sb = new StringBuilder();
            sb.append(topNetCalories_header);
            sb.append(topNetCalories_sep+"\n");
            //going through each entry in sortedEntries
            for (Map.Entry<Integer, Double> entry : sortedEntries) {
                // storing the key which is the ID
                Integer athleteId = entry.getKey();
                //going through each athlete in athletes
                for (Athlete athlete : data.getAllAthletes()) {
                    //checking if this athlete's ID is the same as the key which was stored in athleteId
                    if (athlete.getId() == (athleteId)) {
                        if (athlete instanceof TeamAthlete teamAthlete) {
                            //print the details of that athlete
                            sb.append(String.format(topNetCalories_format, teamAthlete.getName(), teamAthlete.getTeam()));
                            break; // No need to continue searching once found
                        }
                    }
                }
            }
            displayNeededInfo.setText(sb.toString());
        } else {
            //prints if the team not found.
            stautsOfProgram.setText("Team does not exist.");
        }
    }

}
