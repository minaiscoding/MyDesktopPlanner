
package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


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

import model.*;



//____________________for all days ____________________

public class Add_slot1  extends Stage {


	private Spinner<Integer> hoursSpinnerdebut;
	private Spinner<Integer> minutesSpinnerdebut;
	private Spinner<Integer> hoursSpinnerfin;
	private Spinner<Integer> minutesSpinnerfin;
	private AppData appData;
	private User model;
	private GridPane grid ;



   // constructor of the Stage
   public Add_slot1 (AppData appData ) {
	   this.appData = appData;
	   this.setTitle("Add Time Slot");
	   this.setResizable(false);
	   this.model=appData.getCurrentUser();

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


        // ajouter heure début et heure fin

        Label etiq2=creerMessage("debut:");
        grid.add(etiq2, 0, 1, 4, 1);
		Label etiq3=creerMessage(" fin:");
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

		 grid.add(hoursSpinnerdebut,4, 1, 3, 1);
		 grid.add(minutesSpinnerdebut,7,1 , 3, 1);


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

		 grid.add(hoursSpinnerfin,4, 3, 3, 1);
		 grid.add(minutesSpinnerfin,7,3 , 3, 1);


		 Button btn =creerBouton("Add");
		 grid.add(btn ,4,8,3, 1);
		 DataHandler.save(appData);

		 this.setScene(new Scene(grid,300,250));







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
             bouton.setStyle("-fx-background-color: #82a156 ; -fx-text-fill: white;");
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

                             LocalDate startDate= model.getPlanner()	.getFirst_Day();
                         	 LocalDate endDate= model.getPlanner().getLast_Day();
                         	 System.out.println("startDate"+ startDate);
                         	System.out.println("endDate"+ endDate);


                         	 for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1))
                         	 {

                       		 model.getPlanner().getCalendar().add_TimeSlot(date, timeSlot);
                             }
                         	 this.close();

                	    }

                }

 	        });

             return bouton;
          }


}

