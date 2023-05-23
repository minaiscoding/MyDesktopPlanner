package model;
import java.io.Serializable;

public class HistoryPlanner implements Serializable {
    private static final long serialVersionUID = 123456789L;

    private String name;
    private int numBadges;
    private int good;
    private int veryGood;
    private int excellent;
    private int finishedTasks;
    private int finishedProjects;

    public HistoryPlanner(String name, int numBadges, int good, int veryGood, int excellent, int finishedTasks, int finishedProjects) {
        this.name = name;
        this.numBadges = numBadges;
        this.good = good;
        this.veryGood = veryGood;
        this.excellent = excellent;
        this.finishedTasks = finishedTasks;
        this.finishedProjects = finishedProjects;
    }

    public HistoryPlanner() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumBadges() {
        return numBadges;
    }

    public void setNumBadges(int numBadges) {
        this.numBadges = numBadges;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getVeryGood() {
        return veryGood;
    }

    public void setVeryGood(int veryGood) {
        this.veryGood = veryGood;
    }

    public int getExcellent() {
        return excellent;
    }

    public void setExcellent(int excellent) {
        this.excellent = excellent;
    }

    public int getFinishedTasks() {
        return finishedTasks;
    }

    public void setFinishedTasks(int finishedTasks) {
        this.finishedTasks = finishedTasks;
    }

    public int getFinishedProjects() {
        return finishedProjects;
    }

    public void setFinishedProjects(int finishedProjects) {
        this.finishedProjects = finishedProjects;
    }
}
