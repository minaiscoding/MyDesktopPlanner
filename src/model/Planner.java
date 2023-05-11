package model;
import java.time.*;
import java.util.*;

public class Planner {
	private Calendar calendar;
    private int minTaskPerDay;
    private int minTimeSlotMinute;
    private int felicitaions;
    private ArrayList<Badge> badges;


    // constructor, getters, and setters

    public void planifierManuellement(Task task, Date date, LocalTime startTime, LocalTime endTime) {
        // Implementation for manually scheduling a task based on the user's input
    }

    public void planifierAutomatiquement(Task task) {
        // Implementation for automatically scheduling a task based on the user's available time slots and task durations
    }
}
