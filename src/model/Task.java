package model;
import java.util.*;
//abstract
public class Task {
	private static Map<String,String> categories;
    private String nom;
    private int durationInMinutes;
    private PrioTask priority;
    private Date deadline;
    private boolean unscheduled;
    private Status status;
    private String category;

}
