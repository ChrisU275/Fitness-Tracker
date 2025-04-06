package ca.ucalgary.projectcpsc233demo3gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The {@code FileSaver} class provides functionality to save athlete data into a file.
 * This class contains a method to write data from a {@code Data} object, which contains athletes' information,
 * into a specified file in a predefined format.
 */

public class FileSaver {
    /**
     * Saves athlete data from a {@code Data} object into the specified file. The method writes the data into the file,
     * starting with a header "Athletes" followed by each athlete's information on separate lines.
     * Athletes are saved in two formats based on their type: {@code TeamAthlete} with team information, and other athletes without team information.
     * The format for athletes with team information includes the team field, while for others, the team field is omitted.
     *
     * @param file The {@code File} object representing the file where the athlete data should be saved. If the file exists, it will be overwritten.
     * @param data The {@code Data} object containing the list of athletes to be saved.
     * @return {@code true} if the data was successfully saved to the file, {@code false} otherwise.
     * @throws IOException If an error occurs during writing to the file. This exception is caught within the method,
     * and the method returns {@code false}, after printing the stack trace of the exception.
     */
    public static boolean save(File file, Data data) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("Athletes\n");
            List<Athlete> athletes = data.getAllAthletes(); // Assuming getAllStudents() returns List<Student>
            for (Athlete athlete : athletes) {
                if (athlete instanceof TeamAthlete) {
                    fw.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s%n", athlete.getName(), athlete.getId(), athlete.getSport(), ((TeamAthlete) athlete).getTeam(), athlete.getEmail(),athlete.getWeight(),athlete.getHeight(), athlete.getCalorieIntake(),athlete.getCalorieOuttake(),athlete.getNetCalorie(),athlete.getHoursOfWorkout(),athlete.getBmi()));
                }else{
                    fw.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s%n", athlete.getName(), athlete.getId(), athlete.getSport(), athlete.getEmail(),athlete.getWeight(),athlete.getHeight(), athlete.getCalorieIntake(),athlete.getCalorieOuttake(),athlete.getNetCalorie(),athlete.getHoursOfWorkout(),athlete.getBmi()));
                }
            }
            fw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
        return true;
    }
}
