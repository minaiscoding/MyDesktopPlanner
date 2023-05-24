package view;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.layout.GridPane;

import javafx.scene.Scene;

import javafx.scene.control.*;

import javafx.scene.text.Font;

import javafx.stage.Stage;

import model.*;

import javafx.scene.layout.ColumnConstraints;

import javafx.scene.layout.RowConstraints ;

public class Planifier0 extends Stage {

private User model;
private AppData appData;

// constructor of the Stage

public Planifier0 (AppData appData, ArrayList<Task> selectedTasks) {

this.setResizable(false);
this.appData = appData;

this.model=appData.getCurrentUser();

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

Label etiq=creerMessage("How do you want to plan the selected tasks?");

grid.add(etiq, 0, 1, 6, 1);

Button btn1 = new Button("Manual");

btn1.setPrefSize(120,40);

btn1.setFont(Font.font ("Verdana", 15));

btn1.setStyle("-fx-background-color: #82a156 ; -fx-text-fill: white;");

btn1.setOnAction(event -> {
	 for (Task task : selectedTasks) {
     	if(task != null){
     	ChooseTimeView chooseTimeView = new ChooseTimeView(appData,task);
         chooseTimeView.show();}
     }


});

grid.add(btn1, 2, 3, 2, 1);

//##############################################

Button btn2 = new Button("Automatic");

btn2.setPrefSize(120,40);

btn2.setFont(Font.font ("Verdana", 15));

btn2.setStyle("-fx-background-color: #82a156 ; -fx-text-fill: white;");

btn2.setOnAction(event -> {

GridPane G= new PlanAuto(appData,selectedTasks);

this.setScene(new Scene(G,400,350));

});

grid.add(btn2, 2, 4, 2, 1);

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