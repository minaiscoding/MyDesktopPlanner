
package view;
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
import java.time.*;
import java.util.*;




public class Ajout_tache extends Stage {

	private AppData appData;
	private User model;
	private DatePicker deadline;
	private TextField nom;
	private ChoiceBox<String> category, priorityy, decomp;
	private Spinner<Integer> hoursSpinner;
	private Spinner<Integer> minutesSpinner;

   // constructor of the Stage
   public Ajout_tache(AppData appData) {

	   this.setTitle("Add a task");
	   this.setResizable(false);
	   this.model=appData.getCurrentUser();
	   this.appData = appData;


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






       // ajouter le message:
        Label etiq1=creerMessage("Task informations: ");
        etiq1.setStyle(" -fx-underline: true; -fx-text-fill: purple");
        etiq1.setFont(Font.font ("Verdana",20));
        grid.add(etiq1, 0, 0, 9, 1);



        Label etiq2=creerMessage("Name: ");
        grid.add(etiq2, 0, 1, 3, 1);

         nom = new TextField();
        grid.add(nom, 3, 1, 4, 1);

             Label etiq3=creerMessage("Deadline: ");
             grid.add(etiq3, 0, 2, 3, 1);
        // ajouter  DatePicker control
        deadline  = new DatePicker();
        deadline.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate startDate = LocalDate.now(); // D�finir votre jour de d�but ici
                setDisable(empty || date.compareTo(startDate) < 0);
            }
        });

        grid.add(deadline, 3, 2, 4, 1);

        //duration
        Label etiq6=creerMessage("Duration");
        grid.add(etiq6, 0, 3, 2, 1);


      /*  Label etiq7=creerMessage("(HH::MM) ");
        etiq7.setFont(Font.font ("Verdana",8));
        grid.add(etiq7, 1, 3, 2, 1);*/

		 // Create a SpinnerValueFactory for hours from 0 to 23
		 SpinnerValueFactory<Integer> hoursValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);

		 // Create a SpinnerValueFactory for minutes from 0 to 59
		 SpinnerValueFactory<Integer> minutesValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);


		 //heureDebut
		 // Create a Spinner control for hours
		 hoursSpinner = new Spinner<>();
		 hoursSpinner.setValueFactory(hoursValueFactory);

		 // Create a Spinner control for minutes
		 minutesSpinner = new Spinner<>();
		 minutesSpinner.setValueFactory(minutesValueFactory);

		 grid.add(hoursSpinner,3, 3, 2, 1);
		 grid.add(minutesSpinner,5,3 , 2, 1);
        // priority

         Label etiq4=creerMessage("Priority: ");
         grid.add(etiq4, 0, 4, 3, 1);

        priorityy= new ChoiceBox();
        priorityy.getItems().addAll("High", "Medium", "Low");

        grid.add(priorityy, 3, 4, 6, 1);

        // Decomposable
        Label etiq=creerMessage("Decomposable: ");
        grid.add(etiq, 0, 5, 3, 1);

         decomp = new ChoiceBox();
        decomp.getItems().addAll("Yes       ", "No         ");

        grid.add(decomp, 3, 5, 6, 1);


        //Category


        Label etiq5=creerMessage("Category: ");
        grid.add(etiq5, 0, 6, 3, 1);

         category= new ChoiceBox();
        HashSet<Category> cat= model.getPlanner().getCategories();
        if(cat == null){cat = new HashSet<Category>();}
        Iterator<Category> it = cat.iterator();
        while (it.hasNext())
    	{
        	 category.getItems().addAll(it.next().getType());

    	}
       grid.add( category, 3, 6, 5, 1);

       Button bouton = new Button("Add a new Category?");
       bouton.setPrefSize(150,10);
      bouton.setStyle("-fx-background-color: white; -fx-text-fill: purple; -fx-underline: true;");
      bouton.setOnAction(event -> {
   	        Add_Category stage= new Add_Category (this.model);
   	        stage.show();
        
       });
       grid.add(bouton ,5,6,5, 1);






		 // Le boutton Ajouter
		 Button btn =new Button("Add");
		 btn.setPrefSize(160,40);
		 btn.setFont(Font.font ("Verdana", 15));
		 btn.setStyle("-fx-background-color: purple ; -fx-text-fill: white;");
	     btn.setOnAction(event -> {

	    	// if (nom!=null)&&(deadline!=null)&&()
	    	 //nom
	    	 String name= nom.getText();
	    	 //deadline
	    	 LocalDate deadline = this.deadline.getValue();
	    	 //duration
	    	 int hours =hoursSpinner.getValue();
  		     int minutes = minutesSpinner.getValue();
	    	 int duration =  (hours*60)+ minutes;

	    	 //category
	    	 String cate = category.getValue();
	    	 HashSet<Category> catt= model.getPlanner().getCategories();
	    	 if(catt == null){catt = new HashSet<Category>();}
	         Iterator<Category> itt = catt.iterator();
	         Category C;
	         Category categorie = null;
	         while (itt.hasNext())
	     	{
	        	 C= itt.next();
	         	 if (C.getType().equals(cate) )
	         	 {
	         		 categorie=C;
	         	 }

	     	}

	         // priority
	    	  Priority  prio = null;
	    	  if (this.priorityy.getValue().equals("High")) {prio=Priority.HIGH;}
	    	  if (this.priorityy.getValue().equals("Low"))  {  prio=Priority.LOW; }
	    	  if (this.priorityy.getValue().equals("Medium")) {prio=Priority.MEDIUM;}

	    	  // decomposable
	    	  boolean decompsable;
	    	  if (decomp.getValue().equals("Yes       "))
	    	  {
	    		  Task_composed tache= new Task_composed(name, Duration.ofMinutes(duration),prio,deadline,false,Status.notRealised,categorie);

	    	    model.getTasks().add(tache);

	    	  }
	    	  if (decomp.getValue().equals("No         ")) {

	    		  Task_simple tache= new Task_simple(name, Duration.ofMinutes(duration),prio,deadline,false,Status.notRealised,categorie);
	    		  model.getTasks().add(tache);
	    	  }

	    	  this.deadline.setValue(null);
	    	  nom.clear();
	    	  category.getSelectionModel().clearSelection();
	    	  priorityy.getSelectionModel().clearSelection();
	    	  decomp.getSelectionModel().clearSelection();
	    	  hoursSpinner.getValueFactory().setValue(0);
	    	  minutesSpinner.getValueFactory().setValue(0);
	    	  DataHandler.save(appData);
	    	  close();


         	       });




	    	 grid.add(btn ,3,8,4, 1);


	    	 Button btn3 = new Button("Save  ");
	       	   	btn3.setPrefSize(160,40);
	       	    btn3.setFont(Font.font ("Verdana", 15));
	       	    btn3.setStyle("-fx-background-color: purple ; -fx-text-fill: white;");
	       	    btn3.setOnAction(event -> {
	       	      DataHandler.save(appData);
close();

	          	       });

	       	  grid.add(btn3, 3, 9, 4, 1);






		 Scene sc=new Scene(grid,500,400);
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
