
package TP.Control;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import TP.model.*;
import TP.view.*;

public class Controller1  implements  EventHandler<ActionEvent> {
	private Accueil view;
	private User model;
	
	//constructeur:
	 public Controller1 (User model,Accueil view) 
     {
         this.view = view;
         this.model = model;
     }
	 
	// Code exécuté lorsque l'événement survient
	 
	 public void handle(ActionEvent event) 
     {
		 
		 Ajout_Créneau stage=new Ajout_Créneau(model);
         stage.show();   

    }
}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

