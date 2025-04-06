package ca.ucalgary.projectcpsc233demo3gui; /**
 * CPSC 233 W24 Project Menu.java
 * @author Christopher Umukoro, Kenneth Kapungu, Chido Chiradza
 * Tutorial:16
 * Date:1st March 2024
 * @email christopher.umukoro@ucalgary.ca, kenneth.kapungu@ucalgary.ca, chido.chiradza@ucalgary.ca
 * @version 1.0
 */

import java.io.File;
import java.util.*;

import static java.util.stream.Collectors.toList;

//Initializing the menu class
public class Menu {
    private static Data data = new Data();
    //initializing a scanner that can be used through the entire class
    private static final Scanner scanner = new Scanner(System.in);
    //initializing an arraylist that would be used to print the options for the entire menu.
    private static final ArrayList<String> options = new ArrayList<>();

    //Adding the menu layout to the options arraylist using a static block so that it's available for use throughout the entire code
    static {
        options.add("Exit");
        options.add("Enter new athlete info.");
        options.add("BMI of each athlete.");
        options.add("Print all athletes.");
        options.add("Print all sports.");
        options.add("What is info of an athlete?");
        options.add("How many athletes are there in select sports?");
        options.add("Print athlete fitness details");
        options.add("Top athletes in team based on hours worked out.");
        options.add("Top athletes in team based on net calories.");
        options.add("Load");
        options.add("Save");
    }

    //The message to be printed at the start of the program
    private static String optMessage = """
            Store and access athletes and teams for fitness tracking.
            \tMenu Options.
            """;

    //static block responsible for the initalizing the formatted menu
    static {
        //Initializing a variable to efficiently build the menu message using StringBuilder
        StringBuilder sb = new StringBuilder();
        //adding the optmessage to this formatted menu
        sb.append(optMessage);
        // a loop to add each menu option and a number showing which option it is in the menu.
        for (int i = 0; i < options.size(); i++)
            sb.append(String.format("\t%d. %s\n", i, options.get(i)));
        optMessage = sb.toString();
    }

    //main loop of the program the user is going to use to interact
    public static void menuLoop(File file) {
        if (file != null) {
            Load(file);
        }
        System.out.println(optMessage);
        // storing the choice of the user makes and converting it to an integer
        String choice = scanner.nextLine();
        int option = Integer.parseInt(choice);
        // while the user's choice is not exit
        while (option != 0) {
            // using a switch statement to run the required function for that choice based on the user's choice
            switch (option) {
                case 1 -> menuEnterNewAthlete();
                case 2 -> menuAthleteBMI();
                case 3 -> menuPrintAllAthletes();
                case 4 -> menuPrintAllSports();
                case 5 -> menuPrintAthleteInfo();
                case 6 -> menuAthletesInSports();
                case 7 -> menuAthleteFitnessDetails();
                case 8 -> menuTopAthletesHoursWorkedOut();
                case 9 -> menuTopAthletesNetCalories();
                case 10 -> Load();
                case 11 -> Save();
                case 12 -> printAllAthletesData();
                //prints if the user's input is not among the options provided
                default -> System.out.printf("Option %d not recognized!%n", option);
            }
            //repeats the action of asking the user to enter an input in order to run the menu loop again
            System.out.println(optMessage);
            choice = scanner.nextLine();
            option = Integer.parseInt(choice);
        }
        // prints this when the user's input is 0.
        System.out.printf("Thanks for using this program.%nBye!%n");
    }

    private static void Save() {
        String filename;
        File file;
        do {
            do {
                System.out.println("Enter a filename:");
                filename = scanner.nextLine().trim();
            } while (filename.isEmpty());
            file = new File(filename);
        } while (file.exists() & !file.canWrite());
        if (FileSaver.save(file, data)) {
            System.out.printf("Saved to file %s%n", filename);
        } else {
            System.out.printf("Failed to save to file %s%n", filename);
        }

    }

    private static void Load(File file) {
        Data loadeddata = FileLoader.load(file);
        if (data == null) {
            System.err.printf("Failed to load data from file %s%n", file);
        } else {
            System.out.printf("Loaded data from file %s%n", file);
            data = loadeddata;
        }
    }

    private static void Load() {
        String filename;
        File file;
        do {
            do {
                System.out.println("Enter a filename:");
                filename = scanner.nextLine().trim();
            } while (filename.isEmpty());
            file = new File(filename);
        } while (!file.exists() || !file.canRead());
        Data data = FileLoader.load(file);
        if (data == null) {
            System.err.printf("Failed to load data from file %s%n", filename);
        } else {
            System.out.printf("Loaded data from file %s%n", filename);
            Menu.data = data;
        }
    }


    //creating the function for Enter new athlete info.
    private static void menuEnterNewAthlete() {
        boolean success;
        //use a do-while loop to ensure the user's details in entered at least once and then check if it could be stored properly.
        do {
            //gets user's details using helper methods
            String name = getName();
            int id = getId();
            String sport = getSport();
            String team = getTeam();
            double weight = getWeight();
            double height = getHeight();
            String email = getEmail();
            double calorieIntake = getCalorieIntake();
            double calorieOuttake = getCalorieBurnt();
            double netCalorie = getNetCalorie(calorieIntake, calorieOuttake);
            int HoursOfWorkout = getHoursOfWorkout();
            double bodyMassIndex = getBodyMassIndex(weight, height);
            //Attempts to store the user's details in the storesNewAthletes method in Data class
            success = data.storeNewAthletes(name, id, sport, team, email, weight, height, calorieIntake, calorieOuttake, netCalorie, HoursOfWorkout, bodyMassIndex);
        } while (!success);//continues the loop until the user's details is successfully stored
    }

    /**
     * method to calculate the net calorie of an athlete
     *
     * @param calorieIntake
     * @param calorieOuttake
     * @return the difference between calorieIntake and calorieOuttake
     */
    private static double getNetCalorie(double calorieIntake, double calorieOuttake) {
        return calorieIntake - calorieOuttake;
    }

    /**
     * helper method to get number of hours worked out
     *
     * @return an integer of number of hours worked out
     */
    private static Integer getHoursOfWorkout() {
        System.out.println("Enter the number of hours you workout per day?");
        String hours = scanner.nextLine().trim();
        //checks if the number of hours entered is at least 0
        while (Integer.parseInt(hours) <= 0) {
            System.out.println("Number of hours not valid. Please enter a valid number of hours:");
            hours = scanner.nextLine().trim();
        }
        return Integer.parseInt(hours);
    }

    /**
     * helper method to get number of calories burnt
     *
     * @return an integer of the number of calories burnt
     */

    private static double getCalorieBurnt() {
        System.out.println("Enter the number of calories you burnt :");
        String burnt = scanner.nextLine().trim();
        //runs the loop as long as the user didn't enter anything
        while (burnt.isEmpty()) {
            System.out.println("Number of calories burnt not valid. Please enter number of calories burnt:");
            burnt = scanner.nextLine().trim();
        }
        return Integer.parseInt(burnt);
    }

    /**
     * helper method to get the number of calories consumed
     *
     * @return an integer of the number of calories consumed
     */
    private static double getCalorieIntake() {
        System.out.println("Enter the number of calories you consume :");
        String consume = scanner.nextLine().trim();
        //runs the loop as long as the user didn't enter anything
        while (consume.isEmpty()) {
            System.out.println("Number of calories burnt not valid. Please enter number of calories burnt:");
            consume = scanner.nextLine().trim();
        }
        return Integer.parseInt(consume);

    }

    /**
     * helper method to get the ID
     *
     * @return a six digit integer representing the ID
     */
    private static int getId() {
        System.out.println("Enter a 6 Digit ID:");
        String id = scanner.nextLine().trim();
        //runs the loop oif the user didn't enter exactly 6 digits
        while (id.length() != 6) {
            System.out.println("Id not valid. Please enter a valid ID:");
            id = scanner.nextLine().trim();
        }
        return Integer.parseInt(id);
    }

    /**
     * helper method to get the name
     *
     * @return a string of the name
     */
    private static String getName() {
        System.out.println("Enter a name:");
        String name = scanner.nextLine().trim();
        // runs the loop while no name was entered.
        while (name.isEmpty()) {
            System.out.println("Name cannot be empty. Please enter a valid name:");
            name = scanner.nextLine().trim();
        }

        return name;
    }

    /**
     * helper method to get the sport
     *
     * @return a string of the sport
     */
    private static String getSport() {
        System.out.println("Enter a sport:");
        String sport = scanner.nextLine().trim();
        // runs the loop while no sport was entered
        while (sport.isEmpty()) {
            System.out.println("Sport cannot be empty. Please enter a valid sport:");
            sport = scanner.nextLine().trim();
        }
        return sport;
    }

    /**
     * helper method to get the team
     *
     * @return a string of the team
     */
    private static String getTeam() {
        System.out.println("Enter a Team:");
        String team = scanner.nextLine().trim();
        // runs the loop while no team was entered
        while (team.isEmpty()) {
            System.out.println("Team cannot be empty. Please enter a valid Team:");
            team = scanner.nextLine().trim();
        }
        return team;
    }

    /**
     * helper method to get the weight
     *
     * @return a double representing weight
     */
    private static double getWeight() {
        System.out.println("Enter a weight(in kilograms):");
        String weight = scanner.nextLine().trim();
        //runs the loop while no weight was entered
        while (weight.isEmpty()) {
            System.out.println("Weight cannot be empty. Please enter a valid weight:");
            weight = scanner.nextLine().trim();
        }

        return Double.parseDouble(weight);
    }

    /**
     * helper method to get the height
     *
     * @return a double representing height
     */
    private static double getHeight() {
        System.out.println("Enter a Height(in meters):");
        String height = scanner.nextLine().trim();
        //runs the loop while no height was entered
        while (height.isEmpty()) {
            System.out.println("Height cannot be empty. Please enter a valid Height:");
            height = scanner.nextLine().trim();
        }

        return Double.parseDouble(height);
    }

    /**
     * helper method to get the email
     *
     * @return a string of the email
     */
    private static String getEmail() {
        System.out.println("Enter a Gmail or Yahoo Email Address:");
        String email = scanner.nextLine().trim();
        // using a method from the Data class checks if the email entered was valid
        boolean check = Data.checkEmailValid(email);
        //if the check was false it asks the user to re-enter the email address.
        while (check == false) {
            System.out.println("Email must end with either @gmail.com or @yahoo.com. Please enter a valid Email:");
            email = scanner.nextLine().trim();
            check = Data.checkEmailValid(email);
        }
        return email;

    }

    /**
     * helper method to calculate the body mass index
     *
     * @param weight
     * @param height
     * @return the calculation of the weight divided by the height sqauared
     */
    private static double getBodyMassIndex(double weight, double height) {

        double bmi = weight / (height * height);
        //got this from chatgpt to round the value to 3 decimal places
        bmi = Math.round(bmi * 1000.0) / 1000.0;
        return bmi;
    }

    //creating the format for how menuAthleteBMI would be printed to the console.
    private static final String BMI_format = "%-30s\t%-20s\t%-20s\t%-20s\t%-20s%n";
    private static final String BMI_header = String.format(BMI_format, "NAME", "ID", "WEIGHT", "HEIGHT", "BMI");
    private static String BMI_sep = "";

    static {
        for (int i = 0; i < BMI_header.length(); i++) {
            BMI_sep += "-";
        }
    }

    /**
     * function to display the Body Mass Index (BMI) information for a given athlete ID
     */
    private static void menuAthleteBMI() {
        // asks the user to enter their ID
        System.out.println("Enter your id: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        // Check if the athlete with the given ID exists in the data
        if (data.checkExistAthlete(id)) {
            //prints the format
            System.out.println(BMI_header);
            System.out.println(BMI_sep);
            // loop through all athletes in the data
            for (Athlete athlete : data.getAllAthletes()) {
                //Check if the athlete ID matches the entered ID
                if ((int) athlete.getId() == id) {
                    // If the ID matches, print the athlete's details
                    System.out.printf(BMI_format, athlete.getName(), athlete.getId(), athlete.getWeight(), athlete.getHeight(), athlete.getBmi());
                }
            }
        } else {
            //if the athlete does not exist, print message
            System.out.println("Athlete with that id does not exist.");
        }
    }


    //creating the format for how menuPrintAllAthletes would be printed to the console.
    private static final String athlete_format = "%-30s\t%-8s\t%-20s\t%-20s\t%-4s\t%-4s\t%-40s%n";

    private static final String athlete_header = String.format(athlete_format, "NAME", "ID", "SPORT", "TEAM", "WEIGHT", "HEIGHT", "EMAIL");
    private static String athlete_sep = "";

    static {
        for (int i = 0; i < athlete_header.length(); i++) {
            athlete_sep += "-";
        }
    }

    /**
     * Prints information for all athletes stored in the data.
     */
    private static void menuPrintAllAthletes() {
        // Prints the format
        System.out.println(athlete_header);
        System.out.println(athlete_sep);
        // loop through each athlete in the data and print their details
        for (Athlete athlete : data.getAllAthletes()) {
            // Print athlete details using the specified format
            if (athlete instanceof TeamAthlete teamAthlete) {
                System.out.printf(athlete_format, teamAthlete.getName(), teamAthlete.getId(), teamAthlete.getSport(), teamAthlete.getTeam(), teamAthlete.getWeight(), teamAthlete.getHeight(), teamAthlete.getEmail());
            }else{
                System.out.printf(athlete_format, athlete.getName(), athlete.getId(), athlete.getSport(), "null", athlete.getWeight(), athlete.getHeight(), athlete.getEmail());
            }
        }
    }


    /**
     * Prints information for a specific athlete based on their ID.
     */
    private static void menuPrintAthleteInfo() {
        // ask the user to enter their ID
        System.out.println("Enter your id: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        // Check if the athlete with the entered ID exists in the data
        if (data.checkExistAthlete(id)) {
            //prints the format
            System.out.println(athlete_header);
            System.out.println(athlete_sep);
            // loop through all athletes in the data
            for (Athlete athlete : data.getAllAthletes()) {
                // Check if the athlete ID matches the entered ID
                if (athlete.getId() == id) {
                    // If the ID matches, print the athlete's details
                    if (athlete instanceof TeamAthlete teamAthlete) {
                        System.out.printf(athlete_format, teamAthlete.getName(), teamAthlete.getId(), teamAthlete.getSport(), teamAthlete.getTeam(), teamAthlete.getWeight(), teamAthlete.getHeight(), teamAthlete.getEmail());
                    }else{
                        System.out.printf(athlete_format, athlete.getName(), athlete.getId(), athlete.getSport(), "null", athlete.getWeight(), athlete.getHeight(), athlete.getEmail());
                    }
                }
            }
        } else {
            //if the athlete does not exist, print message
            System.out.println("Athlete does not exist.");
        }
    }


    /**
     * Prints all unique sports present in the data.
     */
    private static void menuPrintAllSports() {
        // Get all unique sports using the Data class
        HashSet<String> allSports = new HashSet<>();
        allSports = data.getAllSports();
        // loop through each unique sport and print it
        for (String allSport : allSports) {
            System.out.printf(allSport + "%n");
        }
    }


    //creating the format for how menuAthletesInSports would be printed to the console.
    private static final String InSports_format = "%-30s\t%-8s\t%-20s\t%-20s\t%-20s\t%-20s%n";


    private static final String InSports_header = String.format(InSports_format, "NAME", "TEAM", "CALORIE INTAKE", "CALORIE OUTTAKE", "HOURS OF WORKOUT", "BMI");
    private static String InSports_sep = "";

    static {
        for (int i = 0; i < InSports_header.length(); i++) {
            InSports_sep += "-";
        }
    }

    /**
     * function to print all the athletes in a particular sport in stored data.
     */
    private static void menuAthletesInSports() {
        //asks the user to enter a sport
        System.out.println("Enter a sport: ");
        String sport = scanner.nextLine().trim();
        //checks to see if that sport exists in the stored data
        if (data.checkExistSport(sport)) {
            //prints the format
            System.out.println(InSports_header);
            System.out.println(InSports_sep);
            //goes through each athlete in athletes
            for (Athlete athlete : data.getAllAthletes()) {
                //checks if their sport is equal to the sport entered by the user
                if (athlete.getSport().equals(sport)) {
                    //prints that athlete's details
                    if (athlete instanceof TeamAthlete teamAthlete) {
                        System.out.printf(InSports_format, teamAthlete.getName(), teamAthlete.getTeam(), teamAthlete.getCalorieIntake(), teamAthlete.getCalorieOuttake(), teamAthlete.getHoursOfWorkout(), teamAthlete.getBmi());
                    }else{
                        System.out.printf(InSports_format, athlete.getName(),"null", athlete.getCalorieIntake(), athlete.getCalorieOuttake(), athlete.getHoursOfWorkout(), athlete.getBmi());
                    }
                }


            }
        }
        //prints out this message if the sport not found
        else {
            System.out.println("Sport does not exist.");
        }

    }


    //creating the format for how menuAthleteFitnessDetails would be printed to the console.
    private static final String fitness_format = "%-30s\t%-20s\t%-20s\t%-20s\t%-20s%n";
    private static final String fitness_header = String.format(fitness_format, "NAME", "CALORIE INTAKE", "CALORIE OUTTAKE", "NET CALORIE", "HOURS OF WORKOUT");
    private static String fitness_sep = "";

    static {
        for (int i = 0; i < fitness_header.length(); i++) {
            fitness_sep += "-";
        }
    }

    /**
     * Displays fitness details for a specific athlete based on their ID.
     */
    private static void menuAthleteFitnessDetails() {
        //asks user to enter their ID
        System.out.println("Enter an ID:");
        int id = Integer.parseInt(scanner.nextLine().trim());
        // Check if the athlete with the entered ID exists in the data
        if (data.checkExistAthlete(id)) {
            //print the format
            System.out.println(fitness_header);
            System.out.println(fitness_sep);
            //loop through all athletes in the data
            for (Athlete athlete : data.getAllAthletes()) {
                // Check if the athlete ID matches the entered ID
                if (athlete.getId() == id) {
                    // If the ID matches, print the athlete's fitness details
                    System.out.printf(fitness_format, athlete.getName(), athlete.getCalorieIntake(), athlete.getCalorieOuttake(), athlete.getNetCalorie(), athlete.getHoursOfWorkout());
                }
            }
        } else {
            //if the athlete does not exist, print message
            System.out.println("Athlete does not exist.");
        }
    }


    //creating the format for how menuTopAthletesNetCalories would be printed to the console.
    private static final String topNetCalories_format = "%-30s\t%-8s%n";


    private static final String topNetCalories_header = String.format(topNetCalories_format, "NAME", "TEAM");
    private static String topNetCalories_sep = "";

    static {
        for (int i = 0; i < topNetCalories_header.length(); i++) {
            topNetCalories_sep += "-";
        }
    }

    /**
     * function to calculate the top Athletes in a team based on their net calories.
     */
    private static void menuTopAthletesNetCalories() {
        //asks user for the team they want to calculate for
        System.out.println("Enter the team:");
        String team = scanner.nextLine();
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
            System.out.println(topNetCalories_header);
            System.out.println(topNetCalories_sep);
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
                            System.out.printf(topNetCalories_format, teamAthlete.getName(), teamAthlete.getTeam());
                            break; // No need to continue searching once found
                        }
                    }
                }
            }
        } else {
            //prints if the team not found.
            System.out.println("Team does not exist.");
        }
    }


    //creating the format for how menuTopAthletesHoursWorkedOut would be printed to the console.
    private static final String topHoursWorkedOut_format = "%-30s\t%-8s%n";


    private static final String topHoursWorkedOut_header = String.format(topHoursWorkedOut_format, "NAME", "HOURS WORKED OUT");
    private static String topHoursWorkedOut_sep = "";

    static {
        for (int i = 0; i < topHoursWorkedOut_header.length(); i++) {
            topHoursWorkedOut_sep += "-";
        }
    }

    /**
     * function to calculate the top Athletes in a team based on their hours worked out.
     */
    private static void menuTopAthletesHoursWorkedOut() {
        ArrayList<Athlete> newAthleteList = new ArrayList<>();
        //asks user for the team they want to calculate for
        System.out.println("Enter the team:");
        String team = scanner.nextLine();
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
            System.out.println(topHoursWorkedOut_header);
            System.out.println(topHoursWorkedOut_sep);
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
                            System.out.printf(topHoursWorkedOut_format, teamAthlete.getName(), teamAthlete.getHoursOfWorkout());
                        break;// No need to continue searching once found
                    }
                }
            }
            for (Athlete athlete : newAthleteList) {
                if (athlete instanceof TeamAthlete teamAthlete) {
                    System.out.printf(topHoursWorkedOut_format, teamAthlete.getName(), teamAthlete.getHoursOfWorkout());
                }
            }
        } else {
            //prints if the team not found.3
            System.out.println("Team does not exist.");
        }
    }

    private static void printAllAthletesData() {
        if (data != null) {
            System.out.println("All Loaded Athletes:");
            System.out.println(data.getAllAthletesInfo());
        } else {
            System.out.println("No data loaded.");
        }
    }
}



