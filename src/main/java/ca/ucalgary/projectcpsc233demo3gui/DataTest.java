package ca.ucalgary.projectcpsc233demo3gui; /**
 * CPSC 233 W24 Project Menu.java
 * @author Christopher Umukoro, Kenneth Kapungu, Chido Chiradza
 * Tutorial:16
 * Date:1st March 2024
 * @email christopher.umukoro@ucalgary.ca, kenneth.kapungu@ucalgary.ca, chido.chiradza@ucalgary.ca
 * @version 1.0
 */
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {
    static String name = "Kenny";
    static int id = 1;
    static String sport = "Basketball";
    static String team = "Team A";
    static double weight = 80.5;
    static double height = 1.85;
    static String email = "john@gmail.com";
    static double calorieIntake = 4000.0;
    static double calorieOuttake = 1800.0;
    static double netCalorie = calorieIntake - calorieOuttake;
    static Integer hoursOfWorkout = 3;
    static double bodyMassIndex = weight / (height * height);
    @Test
    void storeNewAthletes() {
        // Test data
        Data data = new Data();
        // Store athlete
        boolean result = data.storeNewAthletes(name, id, sport, team, email,weight, height,  calorieIntake, calorieOuttake, netCalorie, hoursOfWorkout, bodyMassIndex);
        // Check if athlete is stored successfully
        assertTrue(result);

        // Check if the stored athlete's information matches the input
        Athlete storedAthlete = data.getAllAthletes().get(0); // Assuming only one athlete is stored in this test
        if(storedAthlete instanceof TeamAthlete) {
            assertEquals(name, storedAthlete.getName());
            assertEquals(id, storedAthlete.getId());
            assertEquals(sport, storedAthlete.getSport());
            assertEquals(team, ((TeamAthlete) storedAthlete).getTeam());
            assertEquals(weight, storedAthlete.getWeight());
            assertEquals(height, storedAthlete.getHeight());
            assertEquals(email, storedAthlete.getEmail());
            assertEquals(calorieIntake, storedAthlete.getCalorieIntake());
            assertEquals(calorieOuttake, storedAthlete.getCalorieOuttake());
            assertEquals(netCalorie, storedAthlete.getNetCalorie());
            assertEquals(hoursOfWorkout, storedAthlete.getHoursOfWorkout());
            assertEquals(bodyMassIndex, storedAthlete.getBmi());
        }
    }

    @Test
    void testCheckEmailValid1() {
        Data data = new Data();
        // Test invalid email addresses
        String invalidEmail1 = "user@example.com";
        String invalidEmail2 = "user@gmail.net";
        String invalidEmail3 = "user@yahoo.net";

        assertFalse(Data.checkEmailValid(invalidEmail1));
        assertFalse(Data.checkEmailValid(invalidEmail2));
        assertFalse(Data.checkEmailValid(invalidEmail3));
    }
    @Test
    void testCheckEmailValid2() {
        Data data = new Data();
        // Test valid email addresses
        String validEmail1 = "user@gmail.com";
        String validEmail2 = "user@yahoo.com";

        assertTrue(Data.checkEmailValid(validEmail1));
        assertTrue(Data.checkEmailValid(validEmail2));
    }

    @Test
    void testCheckExistAthlete1(){
        Data data = new Data();
        assertTrue(data.storeNewAthletes("brian james",123456,"Football","TeamA","brian.j@gmail.com",1.80,70.0,2000.0,1500.0,500.0,10,22.0));
        assertTrue(data.checkExistAthlete(123456));
    }


    @Test
    void testCheckExistAthlete2(){
        Data data = new Data();
        assertTrue(data.storeNewAthletes("brian james",123456,"Football","TeamA","brian.j@gmail.com",1.80,70.0,2000.0,1500.0,500.0,10,22.0));
        assertFalse(data.checkExistAthlete(264874));
    }


    @Test
    void testGetAllSports() {
        Data data = new Data();

        data.storeNewAthletes("Chris", 123456, "Basketball", "Team A", "chris@gmail.com", 1.75,70.0 , 3000.0, 1500.0, 1500.0, 2, 22.86);
        data.storeNewAthletes("Kenny", 234567, "Football", "Team B", "kenny@gmail.com", 1.70,65.0 , 2500.0, 1200.0, 1300.0, 3, 22.49);
        data.storeNewAthletes("Chido", 345678, "Basketball", "Team C", "chido@gmail.com", 1.80, 75.0, 2800.0, 1600.0, 1200.0, 2, 23.15);

        HashSet<String> sportsList = data.getAllSports();

        assertEquals(2, sportsList.size());
        assertTrue(sportsList.contains("Basketball"));
        assertTrue(sportsList.contains("Football"));
    }
    @Test
    void testCheckExistSport1() {
        Data data = new Data();
        //testing if a valid sport entered exist
        data.storeNewAthletes("Chris", 123456, "Basketball", "Team A", "chris@gmail.com", 1.75,70.0 , 3000.0, 1500.0, 1500.0, 2, 22.86);
        data.storeNewAthletes("Kenny", 234567, "Football", "Team B", "kenny@gmail.com", 1.70, 65.0, 2500.0, 1200.0, 1300.0, 3, 22.49);
        data.storeNewAthletes("Chido", 345678, "Basketball", "Team C", "chido@gmail.com", 1.80, 75.0, 2800.0, 1600.0, 1200.0, 2, 23.15);

        assertTrue(data.checkExistSport("Basketball"));
        assertTrue(data.checkExistSport("Football"));
    }



    @Test
    void testCheckExistSport2() {
        Data data = new Data();
        //testing if an invalid sport entered exists
        data.storeNewAthletes("Chris", 123456, "Basketball", "Team A", "chris@gmail.com", 1.75, 70.0, 3000.0, 1500.0, 1500.0, 2, 22.86);
        data.storeNewAthletes("Kenny", 234567, "Football", "Team B", "kenny@gmail.com", 1.70, 65.0, 2500.0, 1200.0, 1300.0, 3, 22.49);
        data.storeNewAthletes("Chido", 345678, "Basketball", "Team C", "chido@gmail.com", 1.80, 75.0, 2800.0, 1600.0, 1200.0, 2, 23.15);

        assertFalse(data.checkExistSport("Tennis"));
        assertFalse(data.checkExistSport("golf"));
    }
    @Test
    void testCheckExistTeam1() {
        Data data = new Data();
        //testing if a valid team entered exists
        data.storeNewAthletes("Chris", 123456, "Basketball", "Team A", "chris@gmail.com", 1.75,70.0 , 3000.0, 1500.0, 1500.0, 2, 22.86);
        data.storeNewAthletes("Kenny", 234567, "Football", "Team B", "kenny@gmail.com", 1.70,65.0 , 2500.0, 1200.0, 1300.0, 3, 22.49);
        data.storeNewAthletes("Chido", 345678, "Basketball", "Team C", "chido@gmail.com", 1.80, 75.0, 2800.0, 1600.0, 1200.0, 2, 23.15);

        assertTrue(data.checkExistTeam("Team A"));
        assertTrue(data.checkExistTeam("Team B"));
    }
    @Test
    void testCheckExistTeam2() {
        Data data = new Data();
        //testing if an invalid team entered exists
        data.storeNewAthletes("Chris", 123456, "Basketball", "Team A", "chris@gmail.com", 1.75, 70.0, 3000.0, 1500.0, 1500.0, 2, 22.86);
        data.storeNewAthletes("Kenny", 234567, "Football", "Team B", "kenny@gmail.com", 1.70, 65.0, 2500.0, 1200.0, 1300.0, 3,22.49);
        data.storeNewAthletes("Chido", 345678, "Basketball", "Team C", "chido@gmail.com", 1.80, 75.0, 2800.0, 1600.0, 1200.0, 2, 23.15);

        assertFalse(data.checkExistTeam("Barcelona"));
        assertFalse(data.checkExistTeam("Liverpool"));
    }
    @Test
    void testGetTeamList1() {
        Data data = new Data();
        //testing if a TeamList Arraylist stored is the same as the id's entered
        data.storeNewAthletes("Chris", 123456, "Basketball", "Team A", "chris@gmail.com", 1.75,70.0 , 3000.0, 1500.0, 1500.0, 2, 22.86);
        data.storeNewAthletes("Kenny", 234567, "Football", "Team A", "kenny@gmail.com", 1.70, 65.0, 2500.0, 1200.0, 1300.0, 3, 22.49);
        data.storeNewAthletes("Chido", 345678, "Basketball", "Team A", "chido@gmail.com", 1.80, 75.0, 2800.0, 1600.0, 1200.0, 2, 23.15);

        List<Integer> TeamList = data.getTeamList("Team A");

        assertEquals(3, TeamList.size());

        assertTrue(TeamList.contains(123456));
        assertTrue(TeamList.contains(234567));
        assertTrue(TeamList.contains(345678));
    }

}








