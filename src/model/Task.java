

package TP.model;
import java.time.*;


public abstract class Task implements Comparable <Task>{
	private String nom;
	private int  durée; // en minutes 
	private Priority priority;
	private LocalDate deadline;
	private Category category;
	private Status etat;
	private boolean is_scheduled;
	
	
	
	
	public int compareTo (Task t)
    {
        return this.priority.compareTo(t.getPriority());
    }




	public Task(String nom, int durée, Priority priority, LocalDate deadline, Category category,
			boolean is_scheduled) {
		super();
		this.nom = nom;
		this.durée = durée;
		this.priority = priority;
		this.deadline = deadline;
		this.category = category;
		this.is_scheduled = is_scheduled;
	}




	public Priority getPriority() {
		return priority;
	}




	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public void afficher()
	{
		System.out.println("_______________  __");
		System.out.println("Name:"+nom);
		System.out.println("Duration:"+durée+" min");
		System.out.println("Deadline:"+deadline);
		System.out.println("Priority:"+priority);
		System.out.println("Category:"+category.getType());//
		
	}
}
