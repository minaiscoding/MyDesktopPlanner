
package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints ;
import java.time.*;
import javafx.scene.control.DateCell;

import model.*;





public class Add_Planner extends Stage {

	private DatePicker Debut_periode;
	private DatePicker Fin_periode;
	private TextField name;
	private User model;

   public LocalDate getDebut_periode() {
		return Debut_periode.getValue();
	}

	public LocalDate getFin_periode() {
		return Fin_periode.getValue();
	}
	public String getName() {
		return name.getText();
	}


// constructor of the Stage
   public Add_Planner(AppData appdata) {

	   this.setTitle("Ajouter un créneau");
	   this.setResizable(false);
	   this.model=appdata.getCurrentUser();

        // Create a GridPane layout
        GridPane grid = new GridPane();
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



       Label etiq=creerMessage("Planning name : ");
       grid.add(etiq, 0, 0, 4, 1);
       name= new TextField();
       grid.add(name, 4, 0, 4, 1);


       // ajouter date début et date fin

          Label etiq2=creerMessage("First Day:");
            grid.add(etiq2, 0, 2, 3, 1);
          Label etiq3=creerMessage("Last Day:");
            grid.add(etiq3, 0, 4, 3, 1);



        // ajouter  DatePicker control

         Debut_periode = new DatePicker();
         Debut_periode.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate startDate = LocalDate.now(); // Définir votre jour de début ici
                setDisable(empty || date.compareTo(startDate) < 0);
            }
        });
         grid.add(Debut_periode, 4, 2, 4, 1);




         Fin_periode = new DatePicker();
         Fin_periode.setDayCellFactory(picker -> new DateCell() {
             @Override
             public void updateItem(LocalDate date, boolean empty) {
                 super.updateItem(date, empty);
                 LocalDate startDate = Debut_periode.getValue(); // Utilisez la valeur sélectionnée dans le premier DatePicker
                 setDisable(empty || date.compareTo(startDate) < 0);
             }
         });
         Debut_periode.setValue(LocalDate.now());
         Debut_periode.valueProperty().addListener((observable, oldValue, newValue) -> {
             LocalDate startDate = newValue;
             Fin_periode.setDisable(false);
             //Fin_periode.setValue(startDate); // Mettez à jour la valeur du deuxième DatePicker avec la date de début sélectionnée
             Fin_periode.setDayCellFactory(picker -> new DateCell() {
                 @Override
                 public void updateItem(LocalDate date, boolean empty) {
                     super.updateItem(date, empty);
                     setDisable(empty || date.compareTo(startDate) < 0);
                 }
             });
         });
             grid.add(Fin_periode, 4, 4, 4, 1);



        // le boutton Next

		   Button bouton = new Button("Next");
	       bouton.setPrefSize(100,10);
	       bouton.setFont(Font.font ("Verdana", 15));
	       bouton.setStyle("-fx-background-color: purple ; -fx-text-fill: white;");
	       grid.add(bouton ,7,6,3, 1);
	       bouton.setOnAction(event -> {
	            // Basculer vers l'étape suivant: ajouter les créneaux libres:
	    	   // les exceptions:
	        	Planner plan= new Planner(this.getName(),this.getDebut_periode(), this.getFin_periode());
	    		model.setPlanner(plan);
	            GridPane G= new Add_TimeSlots(model);
	            this.setScene(new Scene(G,400,300));

	        });

		   this.setScene(new Scene(grid,400,300));


   }



    // qlq fonction utliles:
    public Label creerMessage(String s) {
    	Label etiquette = new Label(s);
    	etiquette.setAlignment(Pos.CENTER);
    	etiquette.setFont(Font.font ("Verdana",15));
    	etiquette.setLineSpacing(20);
    	return etiquette;
    	}



}

