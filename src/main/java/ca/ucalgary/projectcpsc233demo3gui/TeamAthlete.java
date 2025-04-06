package ca.ucalgary.projectcpsc233demo3gui;

/**
 * CPSC 233 W24 Project Menu.java
 * @author Christopher Umukoro, Kenneth Kapungu, Chido Chiradza
 * Tutorial:16
 * Date:1st March 2024
 * @email christopher.umukoro@ucalgary.ca, kenneth.kapungu@ucalgary.ca, chido.chiradza@ucalgary.ca
 * @version 1.0
 */

/**
 * The {@code TeamAthlete} class represents an athlete who is part of a team. It extends the {@code Athlete} class,
 * adding a team name to the basic athlete information. This class is designed to handle athletes who participate
 * in team sports or events, providing a means to store and retrieve the team name associated with the athlete.
 */
public class TeamAthlete extends Athlete {

    private String team; // The name of the team the athlete belongs to

    /**
     * Constructs a new {@code TeamAthlete} with detailed information including team name.
     * This constructor initializes a {@code TeamAthlete} object with all the information inherited from the {@code Athlete}
     * class along with the team name specific to {@code TeamAthlete}.
     *
     * @param name The name of the athlete.
     * @param id The ID of the athlete.
     * @param sport The sport in which the athlete participates.
     * @param team The name of the team the athlete belongs to.
     * @param email The email address of the athlete.
     * @param weight The weight of the athlete in kilograms.
     * @param height The height of the athlete in meters.
     * @param calorieIntake The daily calorie intake of the athlete.
     * @param calorieOuttake The daily calorie expenditure of the athlete.
     * @param netCalorie The net calorie balance of the athlete (intake - outtake).
     * @param hoursOfWorkout The average hours per day the athlete spends training.
     * @param bmi The Body Mass Index of the athlete.
     */
    public TeamAthlete(String name, int id, String sport, String team, String email, double weight, double height, double calorieIntake, double calorieOuttake, double netCalorie, Integer hoursOfWorkout, double bmi) {
        super(name, id, sport, email, weight, height, calorieIntake, calorieOuttake, netCalorie, hoursOfWorkout, bmi);
        this.team = team;
    }

    /**
     * Retrieves the team name of this athlete.
     *
     * @return A {@code String} representing the name of the team the athlete belongs to.
     */
    public String getTeam() {
        return team;
    }

}
