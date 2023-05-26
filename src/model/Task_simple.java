package model;

import java.time.Duration;
import java.time.LocalDate;

public class Task_simple extends Task {
	/**
	 *
	 */
	private static final long serialVersionUID = -7974460443411463751L;
	private boolean repititiion;

	public Task_simple(String nom, Duration duration, Priority priority, LocalDate deadline, boolean unscheduled,
			Status status, Category category) {
		super(nom, duration, priority, deadline, unscheduled, status, category);
		// TODO Auto-generated constructor stub
	}

	public boolean isRepititiion() {
		return repititiion;
	}

	public void setRepititiion(boolean repititiion) {
		this.repititiion = repititiion;
	}









}