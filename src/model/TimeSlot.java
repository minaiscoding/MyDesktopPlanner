
package TP.model;
import java.time.LocalTime;



public class TimeSlot {
	private float duree;
	private Task task;
	private LocalTime HeureDebut ; 
	private LocalTime HeureFin ;
	private boolean isFree;
    private boolean isBlocked;
	
	public TimeSlot (LocalTime HeureDebut, LocalTime HeureFin)
	{
		this.HeureDebut=HeureDebut;
		this.HeureFin=HeureFin;
		this.isFree=true;
		
		
	}
	
	public  LocalTime getHeureDebut()
	{
		return HeureDebut;
	}
	public  LocalTime getHeureFin()
	{
		return HeureFin;
	}
	
	
	
	public void afficher()
	{
		System.out.println("[ "+this.HeureDebut+" ; "+this.HeureFin+" ]");
		if (isFree == true) {
		System.out.println("Etat: libre");
	      }
		else System.out.println("Etat: occupé");
		System.out.println("______________");
	}


}

