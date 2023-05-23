package model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Map.Entry;

public class Calendar implements Serializable {
    private static final long serialVersionUID = -8297802628843331249L;
  //attributs:
  	private TreeMap<LocalDate,TreeSet<TimeSlot>> timeByDay;



     //les methodes:
  	public Calendar()
  	{}


  	public Calendar(TreeMap<LocalDate, TreeSet<TimeSlot>> timeByDay)
  	{
  		this.timeByDay=timeByDay;

  	}



  	public TreeMap<LocalDate, TreeSet<TimeSlot>> getTimeByDay() {
  		return timeByDay;
  	}



  	public void setTimeByDay(TreeMap<LocalDate, TreeSet<TimeSlot>> creneauParJour) {
  		timeByDay = creneauParJour;
  	}



  	public void add_TimeSlot(LocalDate jour, TimeSlot timeSlot)
  	{
  		TreeSet<TimeSlot> TimeSlot_list= timeByDay.get(jour);
  		if (TimeSlot_list == null )
  		{
  			TimeSlot_list= new TreeSet<TimeSlot> ();
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
  	public void UpdateTimeSlot(LocalDate startDay,TimeSlot timeSlot) {

  	    TreeSet<TimeSlot> timeSlotList = timeByDay.get(startDay);


  	    if (timeSlotList == null) {
  	    	System.out.print("Here");
  	        timeSlotList = new TreeSet<>();
  	        timeByDay.put(startDay, timeSlotList);
  	    }
  	  timeSlotList.remove(timeSlot);

  	    timeSlotList.add(timeSlot);
  	}



  	 public boolean estChevauchee(TimeSlot timeSlot ,TreeSet<TimeSlot> TimeSlot_list )
  	 {
  		 //nous aurons un chauvauchement dans ces deux cas:
  		 //Lorsque l'heure de debut du cr�neau est entre le debut et fin d'un autre cr�neau
  	     //Lorsque l'heure de fin du cr�neau est entre le debut et fin d'un autre cr�neau

  	        boolean chevauche=false;
  	        Iterator<TimeSlot> it = TimeSlot_list.iterator();
  	        while (it.hasNext())
  	        	{
  	        	TimeSlot c = it.next();
  	        	c.afficher();
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


           Iterator<Entry<LocalDate, TreeSet<TimeSlot>>> it_map = timeByDay.entrySet().iterator();
           Entry<LocalDate,TreeSet<TimeSlot>> entry;
           LocalDate jour;
           TreeSet<TimeSlot> TimeSlot_list;

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
