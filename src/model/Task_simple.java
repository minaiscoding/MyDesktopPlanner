package TP.model;

import java.time.LocalDate;

public class Task_simple extends Task {
	
	private boolean répititiion;

	public Task_simple(String nom, int durée, Priority priority, LocalDate deadline, Category category,boolean is_scheduled) 
	{
		super(nom, durée, priority, deadline, category, is_scheduled);

	}


	
	
	
}
