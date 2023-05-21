package TP.model;

import java.time.LocalDate;

public class Task_compsée extends Task implements Décomposable  {
	

	private Task[] Sous_taches;
	
	

	
	
	public Task_compsée(String nom, int durée, Priority priority, LocalDate deadline, Category category,	boolean is_scheduled)
	{
		super(nom, durée, priority, deadline, category, is_scheduled);
	}





	
}
