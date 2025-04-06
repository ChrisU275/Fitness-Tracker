package ca.ucalgary.projectcpsc233demo3gui;

import java.util.Comparator;

public class AthleteHoursWorkedOutComparator implements Comparator<Athlete> {
    @Override
    public int compare(Athlete athlete1, Athlete athlete2){
        int comp = athlete1.getHoursOfWorkout().compareTo(athlete2.getHoursOfWorkout());
        if(comp != 0 ){
            return comp;
        }
        return athlete1.compareTo(athlete2);
    }
}
