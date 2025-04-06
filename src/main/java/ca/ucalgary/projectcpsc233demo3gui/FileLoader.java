package ca.ucalgary.projectcpsc233demo3gui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * The {@code FileLoader} class is designed for reading and processing athlete data from a file.
 * This class contains a method to load athlete data from a specified file into a {@code Data} object.
 */

public class FileLoader {
    /**
     *
     *
     * @param file The {@code File} object representing the file to be read. This file should contain the athlete data to be loaded.
     * @return A {@code Data} object filled with athlete data from the file; returns {@code null} if there is an error reading the file or if the header is incorrect.
     * @throws IOException If an error occurs while reading from the file. This exception is caught and handled within the method, resulting in a printed error message and returning {@code null}.
     */

    public static Data load(File file) {
        Data data = new Data();
        try(Scanner scanner = new Scanner (file)){
            String line = scanner.nextLine();
            if(!line.equals ("Athletes")) {
                System.err.println("Could not find Athletes header!");
                return null;
            }
            while(scanner.hasNextLine()){
                line= scanner.nextLine();
                String[] parts = line.split(",");
                if(parts.length == 12){

                    String name = parts[0];
                    int id = Integer. parseInt (parts[1]);
                    String sport = parts[2];
                    String team = parts[3];
                    String email = parts[4];
                    double weight = Double.parseDouble(parts[5]);
                    double height = Double.parseDouble(parts[6]);
                    double calorieIntake = Double.parseDouble(parts[7]);
                    double calorieOuttake = Double.parseDouble(parts[8]);
                    double netCalorie = Double.parseDouble(parts[9]);
                    Integer hoursOfWorkout = Integer.parseInt (parts[10]);
                    double bodyMassIndex = Double.parseDouble(parts[11]);
                    data.storeNewAthletes(name, id, sport, team, email,weight,height,calorieIntake,calorieOuttake,netCalorie,hoursOfWorkout,bodyMassIndex);
                }else if (parts.length == 11){
                    String name = parts[0];
                    int id = Integer. parseInt (parts[1]);
                    String sport = parts[2];
                    String email = parts[3];
                    String team = "null";
                    double weight = Double.parseDouble(parts[4]);
                    double height = Double.parseDouble(parts[5]);
                    double calorieIntake = Double.parseDouble(parts[6]);
                    double calorieOuttake = Double.parseDouble(parts[7]);
                    double netCalorie = Double.parseDouble(parts[8]);
                    Integer hoursOfWorkout = Integer.parseInt(parts[9]);
                    double bodyMassIndex = Double.parseDouble(parts[10]);
                    data.storeNewAthletes(name, id, sport,team, email, weight,height,calorieIntake,calorieOuttake,netCalorie,hoursOfWorkout,bodyMassIndex);
                }else{
                    System.err.println("Student entry was not the right length");
                }
            }
        }catch (IOException ioe) {
            System.out.println("Exception occurred while reading file!");
            return null;
        }
        return data;
    }

}

