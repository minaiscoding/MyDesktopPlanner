package view;

import java.time.LocalDate;

import javafx.geometry.Insets;

import javafx.geometry.Pos;

import javafx.scene.control.DatePicker;

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

public class New_deadline extends Stage {

private DatePicker deadline;

public LocalDate getdeadline() {

return deadline.getValue();

}

// constructor of the Stage

public New_deadline(Task task) {

this.setTitle("Modify deadline ");

this.setResizable(false);

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

Label etiq1=creerMessage( "This task cannot be scheduled before this deadline");

Label etiq2=creerMessage("propose a new deadline if you want.");

grid.add(etiq1, 0, 0, 10, 1);

grid.add(etiq2, 0, 1, 10, 1);

Label etiq3=creerMessage("New deadline ");

grid.add(etiq3, 0, 3, 3, 1);

deadline= new DatePicker();

deadline.setDayCellFactory(picker -> new DateCell() {

@Override

public void updateItem(LocalDate date, boolean empty) {

super.updateItem(date, empty);

LocalDate startDate = LocalDate.now(); // Définir votre jour de début ici

setDisable(empty || date.compareTo(startDate) < 0);

}

});

grid.add(deadline, 4, 3, 4, 1);

Button bouton = new Button("Add");

bouton.setPrefSize(160,10);

bouton.setFont(Font.font ("Verdana", 15));

bouton.setStyle("-fx-background-color: #82a156 ; -fx-text-fill: white;");

bouton.setOnAction(event -> {

task.setDeadline(getdeadline());

this.close();

});

grid.add(bouton ,1,8,4, 1);

this.setScene(new Scene(grid,500,300));

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