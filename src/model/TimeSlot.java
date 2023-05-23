package model;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class TimeSlot implements Serializable, Comparable<TimeSlot>,Decomposable {
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


    public TimeSlot() {

	}


	@Override
    public int compareTo(TimeSlot other) {
        return this.startTime.compareTo(other.startTime);
    }
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    TimeSlot other = (TimeSlot) obj;
	    return startTime.equals(other.startTime);
	}
	@Override
	public int hashCode() {
		return this.startTime.hashCode();
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



	@Override
	public TimeSlot[] decompose(Duration duree) {
	    TimeSlot oldtime = new TimeSlot(this.getStartTime(), this.getStartTime().plus(duree));
	    LocalTime newEndTime = this.getEndTime();
	    LocalTime newStartTime = this.getStartTime().plus(duree);
	    TimeSlot freetime = new TimeSlot(newStartTime, newEndTime);
	    freetime.setFree(true);
	    oldtime.setFree(false);

	    TimeSlot[] result = new TimeSlot[2];
	    result[0] = oldtime;
	    result[1] = freetime;

	    return result;
	}

}

