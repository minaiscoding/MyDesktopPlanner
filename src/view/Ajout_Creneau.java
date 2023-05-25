


package view;
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
import javafx.scene.control.DateCell;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints ;
import java.time.*;

import controller.*;

public class Ajout_Creneau extends Stage {
	
	private DatePicker day;
	private Spinner<Integer> hoursSpinnerdebut;
	private Spinner<Integer> minutesSpinnerdebut;
	private Spinner<Integer> hoursSpinnerfin;
	private Spinner<Integer> minutesSpinnerfin;
	private User model;
	
	private GridPane grid ;
	
	
	
	
   // constructor of the Stage 
   public Ajout_Creneau(User user) {
	   
	   this.setTitle("Add Time Slot");
	   this.setResizable(false);
	   this.model=user;
        // Create a GridPane layout
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        
        //dimensions of the GridPane
        for (int i = 0; i < 10; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / 3);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 10; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / 4);
            grid.getRowConstraints().add(row);
        }
        
        
       // ajouter le message: 
        Label etiq1=creerMessage("day: ");
        		grid.add(etiq1, 0, 0, 3, 1);


        // ajouter  DatePicker control
              day = new DatePicker();
                day.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate startDate = LocalDate.now(); 
                        LocalDate endDate = model.getPlanner().getLast_Day();
                        if  (endDate != null)
                        {
                        setDisable(empty || date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0);
                        }
                        else {setDisable(empty || date.compareTo(startDate) < 0 );}
                    }
                });
        grid.add(day, 3, 0, 4, 1);
        
        // ajouter heure début et heure fin
        
        

		 
		    Label etiq2=creerMessage("Start:");
	        grid.add(etiq2, 0, 1, 4, 1);
			Label etiq3=creerMessage(" End:");
			 grid.add(etiq3, 0, 3, 4, 1);
		 
		

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
		 
		 grid.add(hoursSpinnerdebut,3, 1, 2, 1);
		 grid.add(minutesSpinnerdebut,6,1 , 2, 1);
        
		 
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
		 
		 grid.add(hoursSpinnerfin,3, 3, 2, 1);
		 grid.add(minutesSpinnerfin,6,3 , 2, 1);
		 
		 
		 Button btn =creerBouton("Add");
		 grid.add(btn ,4,8,3, 1);
		 
		 this.setScene(new Scene(grid,400,350));
        
       
   }
   
   
   public  LocalDate getJour()
   {
	   return day.getValue();
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
             bouton.setStyle("-fx-background-color:  #82a156 ; -fx-text-fill: white;");
             bouton.setFont(Font.font ("Verdana", 15));
             //associer le contrôleur 
             bouton.setOnAction(event -> {
            	 LocalTime HeureDebut =this.getHeureDebut();
                 LocalTime HeureFin =this.getHeureFin();
                 
                 if (HeureFin.compareTo(HeureDebut) < 0)
     		    {
     		    	 Label etiq4=creerMessage(" The end hour must be after the start one !");
     		    	  etiq4.setFont(Font.font ("Verdana",10));
     		    	 System.out.println("fauuuux");
     		    	etiq4.setStyle("-fx-background-color:white;");
     		         grid.add(etiq4, 0, 6, 10, 1);
     		         this.setScene(this.getScene());
     		    }
                 else {
                	     
                	    Duration duration = Duration.between(HeureDebut , HeureFin);
                	    long minutes = duration.toMinutes();
                	    
                	    if (minutes<model.getPlanner().getMinTimeSlotMinute())
                	    {
                	    	 Label etiq4=creerMessage(" A Time Slot must be more than "+model.getPlanner().getMinTimeSlotMinute()+" minutes");
            		    	  etiq4.setFont(Font.font ("Verdana",10));
            		    	  etiq4.setStyle("-fx-background-color:white;");
            		         grid.add(etiq4, 0, 6, 10, 1);
            		         this.setScene(this.getScene());
                	    	
                	    }
                	    else {
                	    	 TimeSlot timeSlot= new TimeSlot(HeureDebut,HeureFin );
                       		 model.getPlanner().getCalendar().add_TimeSlot(day.getValue(), timeSlot);
                             
                         	 this.close();
                	    	
                	    }
                	 
                }
        
 	        });
             
             
             
             return bouton;
          }
 
    
}

