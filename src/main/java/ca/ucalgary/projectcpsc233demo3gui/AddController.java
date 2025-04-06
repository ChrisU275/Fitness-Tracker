package ca.ucalgary.projectcpsc233demo3gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AddController {

    @FXML
    private TextField Id;

    @FXML
    private TextField calorieIntake;

    @FXML
    private TextField calorieOuttake;

    @FXML
    private TextField email;

    @FXML
    private TextField height;

    @FXML
    private TextField hoursWorkedOut;

    @FXML
    private TextField name;

    @FXML

    private TextField sport;

    @FXML
    private TextField team;

    @FXML
    private TextField weight;
    @FXML
    private Label statusLabel;
    private MainController mainController;

    public Data data;
    public void setData(Data data,MainController mainController){
        this.data = data;
        this.mainController = mainController;
    }

    @FXML
    void addNewAthlete(ActionEvent event) {

        String nname = name.getText();
        String stringOfId = Id.getText();
        if(checkIdValid(stringOfId)){
            int iid = Integer.parseInt(stringOfId);
            String ssport = sport.getText();
            String tteam = team.getText();
            double wweight = Double.parseDouble(weight.getText());
            double hheight = Double.parseDouble(height.getText());
            String eemail = email.getText();
            if(checkEmailValid(eemail)){
                double ccalorieIntake = Double.parseDouble(calorieIntake.getText());
                double ccalorieOuttake = Double.parseDouble(calorieOuttake.getText());
                double netCalorie = getNetCalorie(ccalorieIntake,ccalorieOuttake);
                Integer noOfHours = Integer.valueOf(hoursWorkedOut.getText());
                double BMI = getBodyMassIndex(wweight,hheight);
                data.storeNewAthletes(nname,iid,ssport,tteam,eemail,wweight,hheight,ccalorieIntake,ccalorieOuttake,netCalorie,noOfHours,BMI);
                mainController.viewAthletes();
            }
        }
    }
    private double getNetCalorie(double calorieIntake, double calorieOuttake) {
        return calorieIntake - calorieOuttake;
    }
    private double getBodyMassIndex(double weight, double height) {
        double bmi = weight / (height * height);
        //got this from chatgpt to round the value to 3 decimal places
        bmi = Math.round(bmi * 1000.0) / 1000.0;
        return bmi;
    }
    private boolean checkIdValid(String id){
        boolean checker = true;
        if(id.length()!= 6){
            System.out.println("here");
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Invalid length for id.");
            checker = false;
        }
        return checker;
    }
    public boolean checkEmailValid(String email){
        boolean check;
        // checks if the email ends with @gmail.com or @yahoo.com
        if(!(email.endsWith("@gmail.com") || email.endsWith("@yahoo.com"))) {
            check = false;
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Invalid E-mail entered.");
        }else{
            check = true;
        }
        return check;
    }
}
