package TP.view;

import javafx.application.Application;

import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.*;

import TP.model.Calendar;
import TP.model.*;


public class Main extends Application {
	
	public void start(Stage primaryStage) 
    {
		TreeMap<LocalDate, ArrayList<TimeSlot>> CréneauParJour= new TreeMap<LocalDate, ArrayList<TimeSlot>>();
		Calendar calendar= new Calendar (CréneauParJour);
		HashSet<Category> cat= new HashSet<Category>();
		Category C1= new Category("Sport   ");
		Category C2= new Category("Study   ");
		Category C3= new Category("Work   ");
		Category C4= new Category("Hobby   ");
		cat.add(C1);
		cat.add(C2);
		cat.add(C3);
		cat.add(C4);
		cat.add(C3);
		
		
		Planner planner =new Planner(calendar,cat);
		User Nada= new User();
		//Nada.setPlanner(planner);
		Add_Planner stage = new Add_Planner(Nada);
	   //	Ajout_tache stage= new Ajout_tache(Nada);
		stage.show();

		System.out.println("Naaada");
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
		}

}
