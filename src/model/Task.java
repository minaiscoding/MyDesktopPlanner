package model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

//change
public abstract class Task implements Comparable <Task>, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3624447590538536960L;
    private String nom;
    private Duration duration;
    private Priority  priority;
    private LocalDate deadline;
    private boolean is_scheduled;
    private Status status;
    private Category category;


    public Task(String nom, Duration duration, Priority priority, LocalDate deadline, boolean unscheduled, Status status, Category category) {
        this.nom = nom;
        this.duration = duration;
        this.priority = priority;
        this.deadline = deadline;
        this.is_scheduled = unscheduled;
        this.status = status;
        this.category = category;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isscheduled() {
        return is_scheduled;
    }

    public void setscheduled(boolean unscheduled) {
        this.is_scheduled = unscheduled;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public void afficher()
	{
		System.out.println("_______________  __");
		System.out.println("Name:"+nom);
		System.out.println("Duration:"+duration.toMinutes()+" min");
		System.out.println("Deadline:"+deadline);
		System.out.println("Priority:"+priority);
		System.out.println("Category:"+category.getType());//

	}
    @Override

    public int compareTo(Task task) {

    // Comparaison par deadline

    int deadlineComparison = this.deadline.compareTo(task.getDeadline());

    // Si les deadlines sont différentes, retourne le résultat de la comparaison des deadlines

    if (deadlineComparison != 0)

    {

    return deadlineComparison;

    }

    else return this.priority.compareTo(task.getPriority());// Sinon on fait la comparaison parraport priority

    }
}
