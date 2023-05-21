package TP.Control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import TP.model.TimeSlot;
import TP.model.User;
import TP.view.*;

import java.time.*;
import java.time.LocalTime;

public class Buttoncontroller implements  EventHandler<ActionEvent> {
	private Ajout_Créneau view;
	private User model;
	
	//constructeur:
	 public Buttoncontroller(User model,Ajout_Créneau view) 
     {
         this.view = view;
         this.model = model;
     }
	 
	// Code exécuté lorsque l'événement survient
	 
	 public void handle(ActionEvent event) 
     {
       LocalTime HeureDebut =view.getHeureDebut();
       LocalTime HeureFin =view.getHeureFin();
       LocalDate jour = view.getJour();
       TimeSlot timeSlot= new TimeSlot(HeureDebut,HeureFin );
       model.getPlanner().getCalendar().add_TimeSlot(jour, timeSlot);
       view.close();
       
       

    }
}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

