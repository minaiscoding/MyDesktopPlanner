
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





public class Accueil  extends Stage {
	private User model;
	
	
   // constructor of the Stage 
	
	
   public Accueil (User user) {
	   
	   this.setTitle("Accueil ");
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
        Label etiq1=creerMessage("Bienvenue");
        		grid.add(etiq1, 0, 0, 4, 1);



		 
		
		 
		 Button btn2 =creerBouton("Ajouter");
		 grid.add(btn2 ,0,2,2, 1);
		 
		 Button btn3 =creerBouton("Afficher");
		 grid.add(btn3 ,2,2,2, 1);
		 
		 Button btn1 =creerBouton("Quitter");
		 grid.add(btn1 ,5,2,2, 1);
		 
		 this.setScene(new Scene(grid,500,500));
        
       
   }
    
    // qlq fonction utliles:
    
    
    public Label creerMessage(String s) {
    	Label etiquette = new Label(s);
    	etiquette.setAlignment(Pos.CENTER);
    	etiquette.setFont(Font.font ("Verdana",20));
    	etiquette.setLineSpacing(20);
    	return etiquette;
    	}
    
    
    

         
         
         
         public Button creerBouton (String s) 
         {
        	 Button bouton = new Button(s);
        	 bouton.setPrefSize(140,40);
        	 bouton.setFont(Font.font ("Verdana", 15));
        	 
        	 //associer le contrôleur qui convient
        	 if("Ajouter".equals(s))
        	 {
        	 bouton.setOnAction(new Controller1(model, this)); 
        	 }
        	 else{
        		 
        		 if("Afficher".equals(s))
        		 {
        			 bouton.setOnAction(new EventHandler<ActionEvent>()
    			     {
    	        	 public void handle(ActionEvent actionEvent)
    	        	 {
    	        	     model.afficher_créneau();
    			     } });
        		 }
        		 else //Quitter
        		 {
        			     bouton.setOnAction(new EventHandler<ActionEvent>()
        			     {
        	        	 public void handle(ActionEvent actionEvent)
        	        	 {
        	        	 Platform.exit();
        			     } });
        			     
        		 }
        	 }
        	 return bouton;
        	 }
 
    
}


