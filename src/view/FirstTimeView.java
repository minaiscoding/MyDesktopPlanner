package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import controller.*;

public class FirstTimeView {
	private FirstTimeController controller;
	public FirstTimeView(FirstTimeController controller){this.controller = controller;}
	 public void show(Stage primaryStage) {

		 // Create welcome message
	        Text welcomeText = new Text("Welcome "+controller.getAppData().getCurrentUser().getUsername()+ "!\n ");
	        Text planText = new Text(" it looks like this is your first time here \n let's start your first planner\n");

	        welcomeText.setFont(Font.loadFont("file:Lobster-Regular.ttf", 50));
	        welcomeText.setFill(Color.web("#577133"));
	        welcomeText.setStyle("-fx-font-weight: 400; -fx-line-height: 50px; -fx-letter-spacing: 0em; -fx-text-alignment: center;");
	        planText.setFont(Font.font("Livvic", 20));
	        planText.setFill(Color.BLACK);
	       planText.setStyle("-fx-font-weight: 400; -fx-line-height: 50px; -fx-letter-spacing: 0em; -fx-text-alignment: center;");

	        Button nextButton = new Button("Start a new planner");
	        nextButton.setPrefWidth(201);
	        nextButton.setPrefHeight(47);
	        nextButton.setLayoutX(854.5);
	        nextButton.setLayoutY(622);
	        nextButton.setStyle("-fx-background-color: #82a156; -fx-background-radius: 30px; -fx-text-fill: white; -fx-font-weight: bold;");
	        nextButton.setOnAction(event -> {

	        });




	     // Create a layout and add components
	        StackPane layout = new StackPane();
	        layout.setPadding(new Insets(20));
	        layout.setAlignment(Pos.TOP_LEFT); // Align to top-left
	        layout.getChildren().addAll(welcomeText,planText,nextButton);
	     // Set margins for the welcome text and plan text
	        double screenWidth = Screen.getPrimary().getBounds().getWidth();
	        double textMargin = (screenWidth / 2) + (screenWidth / 20); // Adjust the value based on the desired positioning

	        StackPane.setMargin(welcomeText, new Insets(80, 0, 0, textMargin));
	        StackPane.setMargin(planText, new Insets(190, 220, 0,0));


	        StackPane.setMargin(nextButton, new Insets(400, 280, 0,0));
	        StackPane.setAlignment(welcomeText, Pos.TOP_LEFT);
	        StackPane.setAlignment(planText, Pos.TOP_RIGHT);
	        StackPane.setAlignment(nextButton, Pos.TOP_RIGHT);


	        // Create a pane to hold the background image and the layout
	        Pane backgroundPane = new Pane();
	        backgroundPane.setPrefSize(Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());

	        // Load the image from file
	        Image backgroundImage = new Image("file:planner.jpg");

	        // Apply the background image to the pane
	        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,
	                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
	                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	        Background background = new Background(backgroundImg);
	        backgroundPane.setBackground(background);

	        // Create a stack pane to hold the background pane and layout
	        StackPane stackPane = new StackPane();
	        stackPane.getChildren().addAll(backgroundPane, layout);

	        // Set the background color of the stack pane
	        stackPane.setStyle("-fx-background-color: white;");

	        // Create the scene
	        Scene scene = new Scene(stackPane);
	        primaryStage.setTitle("Welcome");
	        primaryStage.setScene(scene);

	        // Maximize the window to full screen
	        primaryStage.setMaximized(true);

	        primaryStage.show();
	    }

}
