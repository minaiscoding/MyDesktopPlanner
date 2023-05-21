package model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Map.Entry;

public class Calendar implements Serializable {
    private static final long serialVersionUID = -8297802628843331249L;
  //attributs:
  	private TreeMap<LocalDate, ArrayList<TimeSlot>> timeByDay;



     //les methodes:
  	public Calendar()
  	{}


  	public Calendar(TreeMap<LocalDate, ArrayList<TimeSlot>> timeByDay)
  	{
  		this.timeByDay=timeByDay;

  	}



  	public TreeMap<LocalDate, ArrayList<TimeSlot>> getTimeByDay() {
  		return timeByDay;
  	}



  	public void setTimeByDay(TreeMap<LocalDate, ArrayList<TimeSlot>> créneauParJour) {
  		timeByDay = créneauParJour;
  	}



  	public void add_TimeSlot(LocalDate jour, TimeSlot timeSlot)
  	{
  		ArrayList<TimeSlot> TimeSlot_list= timeByDay.get(jour);
  		if (TimeSlot_list == null )
  		{
  			TimeSlot_list= new ArrayList<TimeSlot> ();
  			TimeSlot_list.add(timeSlot);
  			timeByDay.put(jour, TimeSlot_list);
  		}

  		else
  		{

  			if (estChevauchee(timeSlot,TimeSlot_list)== false)
  			{
  				TimeSlot_list.add(timeSlot);
  			}
  			else
  			{
  			  System.out.println("erreur, il ya un chauvauchement!!");
  		     }



  		}


  	}


  	 public boolean estChevauchee(TimeSlot timeSlot ,ArrayList<TimeSlot> TimeSlot_list )
  	 {
  		 //nous aurons un chauvauchement dans ces deux cas:
  		 //Lorsque l'heure de debut du créneau est entre le debut et fin d'un autre créneau
  	     //Lorsque l'heure de fin du créneau est entre le debut et fin d'un autre créneau

  	        boolean chevauche=false;
  	        Iterator<TimeSlot> it = TimeSlot_list.iterator();
  	        while (it.hasNext())
  	        	{
  	        	TimeSlot c = it.next();
  	            LocalTime hD = c.getStartTime();
  	            LocalTime hF = c.getEndTime();
  	            LocalTime D=timeSlot.getStartTime();
  	            LocalTime F=timeSlot.getEndTime();

  	            if (((hD.isBefore(D))&&(D.isBefore(hF)))  || ((hD.isBefore(F))&&(F.isBefore(hF))) )
  	            {
  	            	chevauche=true;
  	            	break;
  	            }

  	        }



  	    return chevauche;
  	 }


  	 public void afficher() {


           Iterator<Entry<LocalDate, ArrayList<TimeSlot>>> it_map = timeByDay.entrySet().iterator();
           Entry<LocalDate, ArrayList<TimeSlot>> entry;
           LocalDate jour;
           ArrayList<TimeSlot> TimeSlot_list;

            while (it_map.hasNext())
           {
                entry=it_map.next();
                jour=entry.getKey();

                System.out.println("_____________Le jour: "+jour+"_______________");
                TimeSlot_list =entry.getValue();
                Iterator<TimeSlot> it = TimeSlot_list.iterator();
                while (it.hasNext())
    	        {
              	  it.next().afficher();

    	        }

             }
  	 }
}
