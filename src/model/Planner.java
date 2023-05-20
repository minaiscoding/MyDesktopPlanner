package model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.Alert;


public class Planner implements Serializable {

    private static final long serialVersionUID = -9152579661762683399L;

    private Calendar calendar;
    private int minTaskPerDay=3;
    private int minTimeSlotMinute;
    private int felicitaions;
    private int tasksDone;
    private ArrayList<Badge> badges = new ArrayList<Badge>();

	private LocalDate lastResetDate;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getMinTaskPerDay() {
        return minTaskPerDay;
    }

    public void setMinTaskPerDay(int minTaskPerDay) {
        this.minTaskPerDay = minTaskPerDay;
    }

    public int getMinTimeSlotMinute() {
        return minTimeSlotMinute;
    }

    public void setMinTimeSlotMinute(int minTimeSlotMinute) {
        this.minTimeSlotMinute = minTimeSlotMinute;
    }

    public int getFelicitaions() {
        return felicitaions;
    }

    public void setFelicitaions(int felicitaions) {
        this.felicitaions = felicitaions;
    }

    public int getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(int tasksDone) {
        this.tasksDone = tasksDone;
    }
    public void IncTasksDone(){
    	 LocalDate today = LocalDate.now();
         if (!today.equals(lastResetDate)) {
             // Reset tasksDone count if it's a new day
             tasksDone = 0;
             lastResetDate = today;
         }
         tasksDone++;
    	if(tasksDone%3==0){
    		felicitaions ++;
    		if(felicitaions%5==0){
    			addBadge(Badge.GOOD);
    			}else{
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulation!");
            alert.setHeaderText(null);
            alert.setContentText("You have succesfully reached the minimum number of tasks "+felicitaions+" times today! keep up the hard work");
            alert.showAndWait();}
    	}

    }

    public ArrayList<Badge> getBadges() {
        return badges;
    }

    public void setBadges(ArrayList<Badge> badges) {
        this.badges = badges;
    }

    public void planifierManuellement(Task task, Date date, LocalTime startTime, LocalTime endTime) {
        // Implementation for manually scheduling a task based on the user's input
    }

    public void planifierAutomatiquement(Task task) {
        // Implementation for automatically scheduling a task based on the user's available time slots and task durations
    }

    public void addBadge(Badge badge) {
    	badges.add(badge);
    	if(getNumBadge(badge)%3==0){
    		addBadge(badge.next());
    	}
    	else{
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(badge.toString());
        alert.setHeaderText(null);
        alert.setContentText("You got yourself a new badge ! keep up with the hard work ");
        alert.showAndWait();}

    }

    public int getNumBadge(Badge badge) {
        int count = 0;
        for (Badge b : badges) {
            if (b.equals(badge)) {
                count++;
            }
        }
        return count;
    }
}
