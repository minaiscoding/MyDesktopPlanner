
package view;
import model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints ;






public class Add_TimeSlots extends GridPane{

	private AppData appData;
	private User model;


	public Add_TimeSlots(AppData appdata  )
	{
		appData = appdata;
		this.model=appData.getCurrentUser();

		this.setAlignment(Pos.CENTER);
		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(25, 25, 25, 25));

		  //dimensions of the GridPane
        for (int i = 0; i < 6; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / 3);
            this.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 6; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / 4);
            this.getRowConstraints().add(row);
        }

     // ajouter le message:
        Label etiq1=creerMessage("Your Free Time Slots: ");
        		this.add(etiq1, 0, 0, 4, 1);


        		 Label etiq2=creerMessage("For all days: ");
         		this.add(etiq2, 0, 1, 3, 1);

         	   	 Button btn1 = new Button("Add");
         	   	btn1.setPrefSize(120,40);
         	    btn1.setFont(Font.font ("Verdana", 15));
         	    btn1.setStyle("-fx-background-color:  #82a156 ; -fx-text-fill: white;");
         	    btn1.setOnAction(event -> {
         	    	DataHandler.save(appData);
            	      Add_slot1 stage=new Add_slot1(appData);
                      stage.show();

            	       });

         	   this.add(btn1, 3, 1, 2, 1);



         		 Label etiq3=creerMessage("For specific day:");
          		this.add(etiq3, 0, 2, 3, 1);


          		 Button btn2 = new Button("Add");
          	   	btn2.setPrefSize(120,40);
          	    btn2.setFont(Font.font ("Verdana", 15));
          	    btn2.setStyle("-fx-background-color:  #82a156 ; -fx-text-fill: white;");
          	    btn2.setOnAction(event -> {
          	    	DataHandler.save(appData);
             	      Add_Slot0 stage=new Add_Slot0 (appData);
                       stage.show();

             	       });

          	   this.add(btn2, 3, 2, 2, 1);



          	 Button btn3 = new Button("Save");
       	   	btn3.setPrefSize(120,40);
       	    btn3.setFont(Font.font ("Verdana", 15));
       	    btn3.setStyle("-fx-background-color:  #82a156 ; -fx-text-fill: white;");
       	    btn3.setOnAction(event -> {
       	    	DataHandler.save(appData);
       	    	Stage stage = (Stage) btn3.getScene().getWindow();
       	     stage.close();


          	       });

       	   this.add(btn3, 0, 3, 2, 1);
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


