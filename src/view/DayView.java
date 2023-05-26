
package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import javafx.scene.layout.VBox;

import java.time.*;
import java.util.*;




public class DayView extends Stage {


	private User model;
	private LocalDate date;


   // constructor of the Stage
   public  DayView (User user,LocalDate date) {

	   this.setTitle("Day View");

	   this.model=user;
	   this.setDate(date);


	   TreeSet<TimeSlot> List_timeSlots= model.getPlanner().getCalendar().getTimeByDay().get(date);


	   VBox vbox = new VBox();
       vbox.setSpacing(10);
       vbox.setPadding(new Insets(10));

       Label  etiq1=new Label("Day: "+date);
       etiq1.setAlignment(Pos.CENTER);
 	  etiq1.setFont(Font.font ("Verdana",20));
 	 vbox.getChildren().add(etiq1);
       Iterator<TimeSlot> it = List_timeSlots.iterator();
       while (it.hasNext())
       {
     	 vbox.getChildren().add(createDisplay(it.next()));
    }

		 Scene sc=new Scene(vbox,300,500);
		 this.setScene(sc);


   }

		 private VBox  createDisplay (TimeSlot time) {
		        VBox vbox = new VBox();
		        vbox.setSpacing(5);

		     Label  etiq1=new Label("______________");
		     Label  etiq2=new Label("[ " + time.getStartTime() + " ; " + time.getEndTime() + " ]");
		     Label  etiq3=new Label("is Free: "+time.isFree());
		        if ( ! time.isFree()) {
		        	 Label  etiq4=new Label("Task: " + time.getTask().getNom());
		        	 etiq1.setStyle("-fx-text-fill:"+time.getTask().getCategory().getColor()+";");
		        	 etiq2.setStyle("-fx-text-fill:"+time.getTask().getCategory().getColor()+";");
		        	 etiq3.setStyle("-fx-text-fill:"+time.getTask().getCategory().getColor()+";");
		        	 etiq4.setStyle("-fx-text-fill:"+time.getTask().getCategory().getColor()+";");
		        	  etiq1.setAlignment(Pos.CENTER);
		        	  etiq1.setFont(Font.font ("Verdana",15));
		        	  etiq2.setAlignment(Pos.CENTER);
		        	  etiq2.setFont(Font.font ("Verdana",15));
		        	  etiq3.setAlignment(Pos.CENTER);
		        	  etiq3.setFont(Font.font ("Verdana",15));
		        	  etiq4.setAlignment(Pos.CENTER);
		        	  etiq4.setFont(Font.font ("Verdana",15));


		        	  vbox.getChildren().add(etiq1);
				        vbox.getChildren().add(etiq2);
				        vbox.getChildren().add(etiq3);
				        vbox.getChildren().add(etiq4);

		        } else {
		        	   etiq1.setStyle("-fx-text-fill:green ;");
		        	   etiq2.setStyle("-fx-text-fill:green;");
		        	   etiq3.setStyle("-fx-text-fill:green;");
		        	  vbox.getChildren().add(etiq1);
				        vbox.getChildren().add(etiq2);
				        vbox.getChildren().add(etiq3);
				        etiq1.setAlignment(Pos.CENTER);
			        	  etiq1.setFont(Font.font ("Verdana",15));
			        	  etiq2.setAlignment(Pos.CENTER);
			        	  etiq2.setFont(Font.font ("Verdana",15));
			        	  etiq3.setAlignment(Pos.CENTER);
			        	  etiq3.setFont(Font.font ("Verdana",15));

		        }


		     return vbox;
		    }

		public LocalDate getDate() {
			return date;
		}

		public void setDate(LocalDate date) {
			this.date = date;
		}






}




