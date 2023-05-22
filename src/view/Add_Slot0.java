
package view;
import java.time.LocalDate;

import model.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints ;





public class Add_Slot0  extends Stage {


	private User model;



   // constructor of the Stage
   public Add_Slot0 (User user) {

	   this.setTitle("Add Time Slots");
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




        //################################################

       // ajouter le message:
        Label etiq1=creerMessage("By name: ");
        grid.add(etiq1, 0, 0, 2, 1);

        ChoiceBox<String> Days = new ChoiceBox<String>();
        Days.getItems().addAll("Sunday", "Monday", "Tuesday"," Wednesday", "Thursday","Friday","Saturday");

        grid.add(Days, 3, 0, 2, 1);

        Button btn1 = new Button("Add");
	   	btn1.setPrefSize(120,40);
	    btn1.setFont(Font.font ("Verdana", 15));
	    btn1.setStyle("-fx-background-color: purple ; -fx-text-fill: white;");
	    btn1.setOnAction(event -> {
 	      Add_slot2 stage=new Add_slot2 (model,Days.getValue());
           stage.show();

 	       });

	    grid.add(btn1, 5, 0, 1, 1);


        //##############################################


        Label etiq2=creerMessage("By date: ");
        grid.add(etiq2, 0, 1, 	2, 1);
        // ajouter  DatePicker control

        DatePicker day  = new DatePicker();
        day.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate startDate = model.getPlanner().getFirst_Day();
                LocalDate endDate = model.getPlanner().getLast_Day();
                if  (endDate != null)
                {
                setDisable(empty || date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0);
                }
                else {setDisable(empty || date.compareTo(startDate) < 0 );}
            }
        });

        grid.add(day, 3, 1, 2, 1);









        Button btn2 = new Button("Add");
	   	btn2.setPrefSize(120,40);
	    btn2.setFont(Font.font ("Verdana", 15));
	    btn2.setStyle("-fx-background-color: purple ; -fx-text-fill: white;");
	    btn2.setOnAction(event -> {
 	      Add_slot3 stage=new Add_slot3 (model,day.getValue());
           stage.show();

 	       });

	    grid.add(btn2, 5, 1, 1, 1);
		 Scene sc=new Scene(grid,400,300);
		 this.setScene(sc);


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

