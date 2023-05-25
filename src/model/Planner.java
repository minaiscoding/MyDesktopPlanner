package model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.scene.control.Alert;

public class Planner implements Serializable {
    private static final long serialVersionUID = -9152579661762683399L;

    private Calendar calendar;
    private HashSet<Category> categories;
    private int minTaskPerDay;
    private int minTimeSlotMinute = 30;
    private int tasksDone;
    private int felicitations;
    private ArrayList<Badge> badges;
    private LocalDate first_Day;
    private LocalDate last_Day;
    private String name;
    private LocalDate lastResetDate;
    private Duration minTimeSlot =Duration.ofMinutes(30);

    public Duration getMinTimeSlot() {

    return minTimeSlot;

    }

    public void setMinTimeSlot(Duration minTimeSlot) {

    this.minTimeSlot = minTimeSlot;

    }

    public Planner() {
    }
    public Planner(String name, LocalDate first, LocalDate last )

    {

    this.name=name;

    this.first_Day=first;

    this.last_Day=last;

    //initialiser Calendrier

    TreeMap<LocalDate, TreeSet<TimeSlot>> CreneauParJour= new TreeMap<LocalDate, TreeSet<TimeSlot>>();

    Calendar calen= new Calendar (CreneauParJour);

    this.calendar=calen;

    //initialiser les categories:

    HashSet<Category> cat= new HashSet<Category>();

	Category C1= new Category("Sport   ","blue");
	Category C2= new Category("Study   ","purple");
	Category C3= new Category("Work   ","red");
	Category C4= new Category("Hobby   ","orange");
			cat.add(C1);
			cat.add(C2);
			cat.add(C3);
			cat.add(C4);
			cat.add(C3);

    this.categories=cat;

    }

    public Planner(Calendar calendar, HashSet<Category> categories) {
        this.calendar = calendar;
        this.categories = categories;
    }

    public LocalDate getFirst_Day() {
        return first_Day;
    }

    public void setFirst_Day(LocalDate first_Day) {
        this.first_Day = first_Day;
    }

    public LocalDate getLast_Day() {
        return last_Day;
    }

    public void setLast_Day(LocalDate last_Day) {
        this.last_Day = last_Day;
    }



    public Calendar getCalendar() {
        return calendar;
    }

    public HashSet<Category> getCategories() {
        return categories;
    }

    public void setCategories(HashSet<Category> categories) {
        this.categories = categories;
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
        return felicitations;
    }

    public void setFelicitaions(int felicitations) {
        this.felicitations = felicitations;
    }

    public int getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(int tasksDone) {
        this.tasksDone = tasksDone;
    }

    public void afficher_Calendar() {
        calendar.afficher();
    }

    public void IncTasksDone() {
        LocalDate today = LocalDate.now();
        if (!today.equals(lastResetDate)) {
            tasksDone = 0;
            lastResetDate = today;
        }
        tasksDone++;
        if (tasksDone % 3 == 0) {
            felicitations++;
            if (felicitations % 5 == 0) {
                addBadge(Badge.GOOD);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Congratulation!");
                alert.setHeaderText(null);
                alert.setContentText("You have successfully reached the minimum number of tasks " + felicitations +
                        " times today! Keep up the hard work");
                alert.showAndWait();
            }
        }
    }

    public ArrayList<Badge> getBadges() {
        return badges;
    }

    public void setBadges(ArrayList<Badge> badges) {
        this.badges = badges;
    }

    public void planifierManuellement(Task task, LocalDate date, LocalTime startTime, LocalTime endTime) {
        // Implementation for manually scheduling a task based on the user's input
    }

    public void planifierAutomatiquement(Task task) {
        // Implementation for automatically scheduling a task based on the user's available time slots and task durations
    }

    public void addBadge(Badge badge) {
    	if(badges == null){badges = new ArrayList<Badge>();}
        badges.add(badge);
        if (getNumBadge(badge) % 3 == 0) {
            addBadge(badge.next());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(badge.toString());
            alert.setHeaderText(null);
            alert.setContentText("You got yourself a new badge! Keep up with the hard work");
            alert.showAndWait();
        }
    }

    public int getNumBadge(Badge badge) {
        int count = 0;
        if(badges == null){return 0;}
        for (Badge b : badges) {
            if (b.equals(badge)) {
                count++;
            }
        }
        return count;
    }
    public int getTotalBadges() {
    	if(badges == null){return 0;}
        return badges.size();
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addCategory(Category category) {
		this.categories.add(category);
	}
}
