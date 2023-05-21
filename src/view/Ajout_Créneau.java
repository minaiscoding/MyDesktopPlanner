


package TP.view;
import TP.Control.*;
import TP.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints ;
import java.time.*;





public class Ajout_Créneau extends Stage {
	
	private DatePicker jour;
	private Spinner<Integer> hoursSpinnerdebut;
	private Spinner<Integer> minutesSpinnerdebut;
	private Spinner<Integer> hoursSpinnerfin;
	private Spinner<Integer> minutesSpinnerfin;
	private User model;
	
	
   // constructor of the Stage 
   public Ajout_Créneau(User user) {
	   
	   this.setTitle("Ajouter un créneau");
	   this.setResizable(false);
	   this.model=user;
        // Create a GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        
        //dimensions of the GridPane
        for (int i = 0; i < 6; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / 3);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 6; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / 4);
            grid.getRowConstraints().add(row);
        }
        
        
       // ajouter le message: 
        Label etiq1=creerMessage("Veuillez introduire le jour");
        		grid.add(etiq1, 0, 0, 4, 1);


        // ajouter  DatePicker control
        jour = new DatePicker();
        grid.add(jour, 3, 0, 3, 1);
        
        // ajouter heure début et heure fin
        
        Label etiq2=creerMessage("Heure debut:");
        grid.add(etiq2, 0, 1, 2, 1);
		Label etiq3=creerMessage("Heure fin:");
		 grid.add(etiq3, 0, 2, 2, 1);
		 
		

		 // Create a SpinnerValueFactory for hours from 0 to 23
		 SpinnerValueFactory<Integer> hoursValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
			
		 // Create a SpinnerValueFactory for minutes from 0 to 59
		 SpinnerValueFactory<Integer> minutesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		 
		 
		 //heureDebut 
		 // Create a Spinner control for hours
		 hoursSpinnerdebut = new Spinner<>();
		 hoursSpinnerdebut.setValueFactory(hoursValueFactory);
         
		 // Create a Spinner control for minutes
		 minutesSpinnerdebut = new Spinner<>();
		 minutesSpinnerdebut.setValueFactory(minutesValueFactory);
		 
		 grid.add(hoursSpinnerdebut,3, 1, 1, 1);
		 grid.add(minutesSpinnerdebut,4,1 , 1, 1);
        
		 
		 //heureFin
		 
		// Create a SpinnerValueFactory for hours from 0 to 23
				 SpinnerValueFactory<Integer> hoursValueFactoryfin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
					
				 // Create a SpinnerValueFactory for minutes from 0 to 59
				 SpinnerValueFactory<Integer> minutesValueFactoryfin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		
		 // Create a Spinner control for hours
		 hoursSpinnerfin = new Spinner<>();
		 hoursSpinnerfin.setValueFactory(hoursValueFactoryfin);


		 // Create a Spinner control for minutes
		 minutesSpinnerfin = new Spinner<>();
		 minutesSpinnerfin.setValueFactory(minutesValueFactoryfin);
		 
		 grid.add(hoursSpinnerfin,3, 2, 1, 1);
		 grid.add(minutesSpinnerfin,4,2 , 1, 1);
		 
		 
		 Button btn =creerBouton("Ajouter");
		 grid.add(btn ,3,3,3, 1);
		 
		 this.setScene(new Scene(grid,450,400));
        
       
   }
   
   
   public  LocalDate getJour()
   {
	   return jour.getValue();
   }
   
   public  LocalTime getHeureDebut()
   {
	   int hours =hoursSpinnerdebut.getValue();
	   int minutes = minutesSpinnerdebut.getValue();
	   LocalTime time = LocalTime.of(hours, minutes).withSecond(0);
	   return time;

	   
   }
   
   public  LocalTime getHeureFin()
   {
	   int hours =hoursSpinnerfin.getValue();
	   int minutes = minutesSpinnerfin.getValue();
	   LocalTime time = LocalTime.of(hours, minutes).withSecond(0);
	   return time;
   }
   
   
       

    
    
    
    
    
    
    
    
    // qlq fonction utliles:
    
    
    public Label creerMessage(String s) {
    	Label etiquette = new Label(s);
    	etiquette.setAlignment(Pos.CENTER);
    	etiquette.setFont(Font.font ("Verdana",15));
    	etiquette.setLineSpacing(20);
    	return etiquette;
    	}
    
    
    

         public Button creerBouton(String s) {
             Button bouton = new Button(s);
             bouton.setPrefSize(140,40);
             bouton.setFont(Font.font ("Verdana", 15));
             //associer le contrôleur 
             bouton.setOnAction(new Buttoncontroller(model, this)); 
             return bouton;
          }
 
    
}

