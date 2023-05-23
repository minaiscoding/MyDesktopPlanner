package model;
import java.io.Serializable;
import java.time.LocalTime;

public class TimeSlot implements Serializable {
    private static final long serialVersionUID = 8539494544992388816L;
    private LocalTime startTime;
    private LocalTime endTime;
    private Task task;
    private boolean isFree;
    private boolean isBlocked;

    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    
	public int compareTo ( TimeSlot t)
    {
        return this. getStartTime().compareTo(t. getStartTime());
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public TimeSlot ajoutTache(Task tache) {
        // Add your implementation here
        return null;
    }
    public void afficher()
	{
		System.out.println("[ "+this.startTime+" ; "+this.endTime+" ]");
		if (isFree == true) {
		System.out.println("Etat: libre");
	      }
		else System.out.println("Etat: occup�");
		System.out.println("______________");
	}
}

