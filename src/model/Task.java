package model;
import java.io.Serializable;
import java.util.*;

public class Task implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 3624447590538536960L;
	private String nom;
    private int durationInMinutes;
    private PrioTask priority;
    private Date deadline;
    private boolean unscheduled;
    private Status status;
    private String category;

    public Task(String nom, int durationInMinutes, PrioTask priority, Date deadline, boolean unscheduled, Status status, String category) {
        this.nom = nom;
        this.durationInMinutes = durationInMinutes;
        this.priority = priority;
        this.deadline = deadline;
        this.unscheduled = unscheduled;
        this.status = status;
        this.category = category;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public PrioTask getPriority() {
        return priority;
    }

    public void setPriority(PrioTask priority) {
        this.priority = priority;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isUnscheduled() {
        return unscheduled;
    }

    public void setUnscheduled(boolean unscheduled) {
        this.unscheduled = unscheduled;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
