package model;
import java.time.Duration;
import java.time.LocalDate;

public class Task_composed extends Task implements Decomposable  {


	public Task_composed(String nom, Duration duration, Priority priority, LocalDate deadline, boolean unscheduled,
			Status status, Category category) {
		super(nom, duration, priority, deadline, unscheduled, status, category);
		// TODO Auto-generated constructor stub
	}

	private Task[] Sous_taches;

	@Override

	public Task_composed [] decompose(Duration duree) {

	Duration newdur=this.getDuration().minus(duree);

	Task_composed oldtask = new Task_composed(this.getNom(),newdur,this.getPriority(),this.getDeadline(),false,Status.notRealised,this.getCategory());

	Task_composed newtask =  new Task_composed(this.getNom(),newdur,this.getPriority(),this.getDeadline(),false,Status.notRealised,this.getCategory());

	Task_composed [] result = new Task_composed [2];

	result[0] = oldtask;

	result[1] = newtask;

	return result;

	}














}