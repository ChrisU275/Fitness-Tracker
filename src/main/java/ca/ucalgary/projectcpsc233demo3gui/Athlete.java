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
 * The Athlete class represents an athlete with properties such as name, ID, sport,
 * weight, height, email, calorie intake, calorie outtake, net calorie balance, hours
 * of workout, and Body Mass Index (BMI). This class provides a constructor for creating
 * an instance of an athlete with these properties and includes getter methods for
 * retrieving the values of each property.
 */
public class Athlete implements Comparable<Athlete>{
    private String name;
    private int id;
    private String sport;
    private double weight;
    private double height;
    private String email;
    private double calorieIntake;
    private double calorieOuttake;
    private double netCalorie;
    private Integer hoursOfWorkout;
    private double bmi;

    /**
     * Constructs an Athlete object with specified details.
     *
     * @param name the name of the athlete
     * @param id the ID of the athlete
     * @param sport the sport the athlete participates in
     * @param email the email address of the athlete
     * @param weight the weight of the athlete in kilograms
     * @param height the height of the athlete in meters
     * @param calorieIntake the daily calorie intake of the athlete
     * @param calorieOuttake the daily calorie outtake of the athlete
     * @param netCalorie the net calorie balance (intake - outtake) of the athlete
     * @param hoursOfWorkout the daily hours spent on workouts by the athlete
     * @param bmi the Body Mass Index of the athlete
     */
    public Athlete(String name, int id, String sport, String email, double weight, double height, double calorieIntake, double calorieOuttake,
                   double netCalorie, Integer hoursOfWorkout, double bmi) {
        this.name = name;
        this.id = id;
        this.sport = sport;
        this.weight = weight;
        this.height = height;
        this.email = email;
        this.calorieIntake = calorieIntake;
        this.calorieOuttake = calorieOuttake;
        this.netCalorie = netCalorie;
        this.hoursOfWorkout = hoursOfWorkout;
        this.bmi = bmi;
    }

    /**
     * Returns the name of the athlete.
     *
     * @return the name of the athlete
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the athlete.
     *
     * @return the ID of the athlete
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the sport the athlete participates in.
     *
     * @return the sport of the athlete
     */
    public String getSport() {
        return sport;
    }

    /**
     * Returns the weight of the athlete in kilograms.
     *
     * @return the weight of the athlete
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the height of the athlete in meters.
     *
     * @return the height of the athlete
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the email address of the athlete.
     *
     * @return the email address of the athlete
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the daily calorie intake of the athlete.
     *
     * @return the daily calorie intake
     */
    public double getCalorieIntake() {
        return calorieIntake;
    }

    /**
     * Returns the daily calorie outtake of the athlete.
     *
     * @return the daily calorie outtake
     */
    public double getCalorieOuttake() {
        return calorieOuttake;
    }

    /**
     * Returns the net calorie balance (intake - outtake) of the athlete.
     *
     * @return the net calorie balance
     */
    public double getNetCalorie() {
        return netCalorie;
    }

    /**
     * Returns the daily hours spent on workouts by the athlete.
     *
     * @return the daily hours of workout
     */
    public Integer getHoursOfWorkout() {
        return hoursOfWorkout;
    }

    /**
     * Returns the Body Mass Index of the athlete.
     *
     * @return the BMI of the athlete
     */
    public double getBmi() {
        return bmi;
    }

    public int compareTo(Athlete a ){
        return Integer.compare(this.id,a.id);
    }
}
