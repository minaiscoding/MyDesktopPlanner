package view;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;

import javafx.scene.control.Alert;

import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.DateCell;

import javafx.scene.control.DatePicker;

import javafx.scene.text.Font;

import model.*;

import javafx.scene.layout.ColumnConstraints;

import javafx.scene.layout.RowConstraints ;

public class PlanAuto extends GridPane{
	private AppData appData;
	private ArrayList<Task> selectedTasks;

private User model;

private DatePicker Debut_periode;

private DatePicker Fin_periode;

private ChoiceBox<String> blocked;

public LocalDate getDebut_periode() {

return Debut_periode.getValue();

}

public LocalDate getFin_periode() {

return Fin_periode.getValue();

}

public PlanAuto (AppData appData,ArrayList<Task> selectedTasks )

{
this.appData = appData;
this.selectedTasks = selectedTasks;
this.model=appData.getCurrentUser();

this.setAlignment(Pos.CENTER);

this.setHgap(10);

this.setVgap(10);

this.setPadding(new Insets(25, 25, 25, 25));

//dimensions of the GridPane

for (int i = 0; i < 10; i++) {

ColumnConstraints column = new ColumnConstraints();

column.setPercentWidth(100.0 / 3);

this.getColumnConstraints().add(column);

}

for (int i = 0; i < 10; i++) {

RowConstraints row = new RowConstraints();

row.setPercentHeight(100.0 / 4);

this.getRowConstraints().add(row);

}

// ajouter le message:

Label etiq1=creerMessage("If you want you can fix a peride ");

this.add(etiq1, 0, 0, 10, 1);

Label etiq=creerMessage(" to plan thos task(s): ");

this.add(etiq, 0, 1, 10, 1);

Label etiq2=creerMessage("Start Day:");

this.add(etiq2, 0, 3, 3, 1);

Label etiq3=creerMessage("End Day:");

this.add(etiq3, 0, 4, 3, 1);

// ajouter DatePicker control

Debut_periode = new DatePicker();

Debut_periode.setDayCellFactory(picker -> new DateCell() {

@Override

public void updateItem(LocalDate date, boolean empty) {

super.updateItem(date, empty);

LocalDate startDate = LocalDate.now(); // D�finir votre jour de d�but ici

setDisable(empty || date.compareTo(startDate) < 0);

}

});

this.add(Debut_periode, 4, 3, 4, 1);

Fin_periode = new DatePicker();

Fin_periode.setDayCellFactory(picker -> new DateCell() {

@Override

public void updateItem(LocalDate date, boolean empty) {

super.updateItem(date, empty);

LocalDate startDate = Debut_periode.getValue(); // Utilisez la valeur s�lectionn�e dans le premier DatePicker

setDisable(empty || date.compareTo(startDate) < 0);

}

});

Debut_periode.setValue(LocalDate.now());

Debut_periode.valueProperty().addListener((observable, oldValue, newValue) -> {

LocalDate startDate = newValue;

Fin_periode.setDisable(false);

//Fin_periode.setValue(startDate); // Mettez � jour la valeur du deuxi�me DatePicker avec la date de d�but s�lectionn�e

Fin_periode.setDayCellFactory(picker -> new DateCell() {

@Override

public void updateItem(LocalDate date, boolean empty) {

super.updateItem(date, empty);

setDisable(empty || date.compareTo(startDate) < 0);

}

});

});

this.add(Fin_periode, 4, 4, 4, 1);

Label etiq4=creerMessage("Block the TimeSlot for this Task? ");

this.add(etiq4, 0, 6, 8, 1);

blocked = new ChoiceBox();

blocked.getItems().addAll("Yes ", "No ");

this.add(blocked, 8, 6, 2, 1);

Button btn1 = new Button("Plan");

btn1.setPrefSize(170,40);

btn1.setFont(Font.font ("Verdana", 15));

btn1.setStyle("-fx-background-color: #82a156 ; -fx-text-fill: white;");

btn1.setOnAction(event -> {

boolean plan = model.planifier(task);

if (plan) {

Alert alert = new Alert(Alert.AlertType.INFORMATION);

alert.setTitle("Automatic Planning");

alert.setHeaderText(null);

alert.setContentText("Your task was well planned.");

alert.showAndWait();

}


else {

if (task instanceof Task_simple)

{

Alert alert = new Alert(Alert.AlertType.INFORMATION);

alert.setTitle("Automatic Planning");

alert.setHeaderText(null);

alert.setContentText("This task cannot be scheduled before this deadline, propose a new deadline if you want.");

alert.showAndWait();

}


else// composed Task

{

Alert alert = new Alert(Alert.AlertType.INFORMATION);

alert.setTitle("Automatic Planning");

alert.setHeaderText(null);

alert.setContentText("This task cannot be scheduled before this deadline. Do you want to decompose it into subtasks?");

alert.showAndWait();


model.planifierComposed((Task_composed) task);


}



}




//model.planifier();

});

this.add(btn1, 3, 8, 4, 1);

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