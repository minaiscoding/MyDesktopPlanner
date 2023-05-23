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
	public Task[] decompose(Duration duree) {
		// TODO Auto-generated method stub
		return null;
	}














}