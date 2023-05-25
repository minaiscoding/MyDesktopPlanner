
package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints ;



public class Add_Category extends Stage {
	

	private TextField type;
	private User model;
	ChoiceBox<String> choicebox;
	
  
	public String getName() {
		return type.getText();
	}

	public String getColor() {
		return choicebox.getValue();
	}

// constructor of the Stage 
   public Add_Category(AppData appdata) {
	   
	   this.setTitle("Add_Category");
	   this.setResizable(false);
	   this.model=appdata.getCurrentUser();
	   
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
        
        
        String test="green";
       Label etiq=creerMessage("Category type: ");
       etiq.setStyle("-fx-background-color: " +  test + ";");
       grid.add(etiq, 0, 0, 3, 1);
       
       type= new TextField(); 
       grid.add(type, 3, 0, 2, 1);
       
       Label etiq2=creerMessage("Category Color : ");
       grid.add(etiq2, 0, 1, 3, 1);
       choicebox = new ChoiceBox<>();
       choicebox.getItems().addAll(
             
               "red",
               "green",
                "blue",
               "yellow",
               "purple",
              "orange",
               "gray",
               "cyan"
           
       );
  
       
       grid.add(choicebox,3 , 1, 2, 1);
       
       
       
		   Button bouton = new Button("Add");
	       bouton.setPrefSize(160,10);
	       bouton.setFont(Font.font ("Verdana", 15));
	       bouton.setStyle("-fx-background-color: #82a156 ; -fx-text-fill: white;");
	         
	       bouton.setOnAction(event -> {
	    	   Category category =new Category(this.getName(),this.getColor());
	    	   model.getPlanner().addCategory(category);
	         
	        });
	       grid.add(bouton ,1,3,4, 1);   
	       
		   this.setScene(new Scene(grid,350,250));
         
       
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

