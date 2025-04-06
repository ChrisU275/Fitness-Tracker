package ca.ucalgary.projectcpsc233demo3gui; /**
 * CPSC 233 W24 Project Menu.java
 * @author Christopher Umukoro, Kenneth Kapungu, Chido Chiradza
 * Tutorial:16
 * Date:1st March 2024
 * @email christopher.umukoro@ucalgary.ca, kenneth.kapungu@ucalgary.ca, chido.chiradza@ucalgary.ca
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.HashSet;

//initailizing the data class
public class Data {

    // creating the an arraylist of to store all athletes
    private  ArrayList<Athlete> athletes;
    private  ArrayList<Integer> TeamList;

    private  HashSet<String> allSports;
    private HashSet<String> allTeams;


    public Data(){
        athletes = new ArrayList<>();
        TeamList = new ArrayList<>();
        allSports = new HashSet<>();
    }


    /**
     * method to store the athlete's details which have been entered.
     * @param name
     * @param id
     * @param sport
     * @param team
     * @param weight
     * @param height
     * @param email
     * @param calorieIntake
     * @param calorieOuttake
     * @param netCalorie
     * @param hoursOfWorkout
     * @param bodyMassIndex
     * @return a boolean which is true if the data was stored successfully
     */
    public boolean storeNewAthletes(String name, int id, String sport, String team,  String email,double weight, double height, double calorieIntake, double calorieOuttake, double netCalorie, Integer hoursOfWorkout, double bodyMassIndex) {
        Athlete athlete;
        if (team == null) {
            athlete = new Athlete(name, id, sport, email, weight, height, calorieIntake, calorieOuttake, netCalorie, hoursOfWorkout, bodyMassIndex);
        } else {
            athlete = new TeamAthlete(name, id, sport, team, email, weight, height, calorieIntake, calorieOuttake, netCalorie, hoursOfWorkout, bodyMassIndex);
        }
        return storeNewAthlete(athlete);
    }
    public boolean storeNewAthlete(Athlete athlete) {
        athletes.add(athlete);
        System.out.println("Stored!");
        return true;
    }

    /**
         * method created so we have access to all the athletes that have been entered without being able to alter them.
         * @return an arraylist of object lists
         */
    public ArrayList<Athlete> getAllAthletes() {
        return athletes;
    }

    /**
     * method to check if the email entered is valid
     * @param email
     * @return a boolean if the email parameter is valid
     */
    public static boolean checkEmailValid(String email){
        boolean check;
        // checks if the email ends with @gmail.com or @yahoo.com
        if(!(email.endsWith("@gmail.com") || email.endsWith("@yahoo.com"))) {
            check = false;
        }else{
            check = true;
        }
        return check;
    }

    /**
     * method to check if an athlete with the given ID exists.
     *
     * @param id The ID of the athlete to check for existence.
     * @return a boolean that shows if the athlete exists
     */
    public boolean checkExistAthlete(int id) {
        // loop through each athlete in the athletes array
        for(Athlete athlete:athletes){
            // Check if the ID of the current athlete matches the given ID
            if(athlete.getId() == (id)){
                // If a match is found, return true
                return true;
            }
        }
        // If no match is found, return false
        return false;

    }

    /**
     * method to retrieve all unique sports participated in by athletes.
     * @return all unique sports participated in by athletes
     */
    public  HashSet<String> getAllSports() {
        /// Create a HashSet to store unique sports
        // loop through each athlete in the athletes array
        for(Athlete athlete :athletes){
            // Add the sport of the current athlete to the HashSet
            allSports.add(athlete.getSport());
        }
        // Return the HashSet containing all unique sports
        return allSports;

    }

    public  HashSet<String> getAllTeams() {
        /// Create a HashSet to store unique sports
        // loop through each athlete in the athletes array
        for(Athlete athlete :athletes){
            // Add the sport of the current athlete to the HashSet
            if(athlete instanceof TeamAthlete teamAthlete){
                allTeams.add(teamAthlete.getTeam());
            }
        }
        // Return the HashSet containing all unique sports
        return allTeams;
    }

    /**
     * method to check if the sport entered is valid if you want to retrieve data based on sports
     * @param sport
     * @return a boolean that shows if the sport is valid
     */
    public boolean checkExistSport(String sport){
        //checks through each athlete in athletes list
        for(Athlete athlete:athletes){
            //checks through all the values at INDEX_SPORT to see if the sport exists
            if(athlete.getSport().equals(sport)){
                return true;
            }
        }
        return false;
    }


    /**
     * method to check if the team entered is valid if you want to retrieve data based on team
     * @param team
     * @return a boolean that shows if the team exists
     */
    public boolean checkExistTeam(String team){
        for(Athlete athlete:athletes){
            if (athlete instanceof TeamAthlete teamAthlete) {
                if (teamAthlete.getTeam().equals(team)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * method to get the list of the IDs of athletes in a team.
     * @param newteam takes the team we're trying to get a list of IDs for
     * @return an arrayList of integers containing the IDs of all the athletes in that team.
     */
    public ArrayList<Integer> getTeamList(String newteam) {
        for (Athlete athlete : getAllAthletes()) {
            if (athlete instanceof TeamAthlete teamAthlete) {
                if (teamAthlete.getTeam().equals(newteam)) {
                    TeamList.add(athlete.getId());
                }
            }
        }
        return TeamList;
    }

    public String getAllAthletesInfo() {
        StringBuilder builder = new StringBuilder();
        for (Athlete athlete : this.athletes) {
            // Assuming Athlete has a toString method that formats its information.
            builder.append(athlete.toString()).append("\n");
        }
        return builder.toString();
    }
}

