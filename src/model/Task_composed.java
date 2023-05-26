package model;
import java.time.Duration;
import java.time.LocalDate;

public class Task_composed extends Task implements Decomposable  {


	/**
	 *
	 */
	private static final long serialVersionUID = -8136798667839349446L;

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

	public Task[] getSous_taches() {
		return Sous_taches;
	}

	public void setSous_taches(Task[] sous_taches) {
		Sous_taches = sous_taches;
	}














}