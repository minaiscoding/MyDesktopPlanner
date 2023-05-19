package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.*;
import controller.*;

public class WelcomePageView {
private WelcomeController controller;
public void setController(WelcomeController welcomeController) {
	this.controller = welcomeController;

}


    public void show(Stage primaryStage) {
        // Create welcome message
        Text welcomeText = new Text("Welcome to your planner!\n");
        Text nameText = new Text("Please enter your name so we can start planning...");
        welcomeText.setFont(Font.loadFont("file:Lobster-Regular.ttf", 50));
        welcomeText.setFill(Color.web("#577133"));
        welcomeText.setStyle("-fx-font-weight: 400; -fx-line-height: 50px; -fx-letter-spacing: 0em; -fx-text-alignment: center;");
        nameText.setFont(Font.font("Livvic", 20));
        nameText.setFill(Color.BLACK);
       nameText.setStyle("-fx-font-weight: 400; -fx-line-height: 50px; -fx-letter-spacing: 0em; -fx-text-alignment: center;");



        // Create a text field for user input
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");
        nameField.setFont(Font.font("Livvic", 16));
        nameField.setStyle("-fx-font-size: 12px; -fx-pref-width: 50px; -fx-pref-height: 50px; -fx-padding: 2px;");
     // Create a "Next" button
        Button nextButton = new Button("Next");
        nextButton.setPrefWidth(201);
        nextButton.setPrefHeight(47);
        nextButton.setLayoutX(854.5);
        nextButton.setLayoutY(622);
        nextButton.setStyle("-fx-background-color: #82a156; -fx-background-radius: 30px; -fx-text-fill: white; -fx-font-weight: bold;");
        nextButton.setOnAction(event -> {
            String name = nameField.getText();
            controller.verifyUser(name);
            nameField.clear(); // Clear the text field
        });



        // Create a layout and add components
        StackPane layout = new StackPane();
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_LEFT); // Align to top-left
        layout.getChildren().addAll(welcomeText,nameText, nameField,nextButton);

        // Set margins for the welcome text and text field
        StackPane.setMargin(welcomeText, new Insets(80, 120, 0, 0));
        StackPane.setMargin(nameText, new Insets(190, 120, 0, 0));
        StackPane.setMargin(nameField, new Insets(300, 0, 0,600));
        StackPane.setMargin(nextButton, new Insets(400, 280, 0,0));
        StackPane.setAlignment(welcomeText, Pos.TOP_RIGHT);
        StackPane.setAlignment(nameText, Pos.TOP_RIGHT);
        StackPane.setAlignment(nextButton, Pos.TOP_RIGHT);

        // Create a pane to hold the background image and the layout
        Pane backgroundPane = new Pane();
        backgroundPane.setPrefSize(643, Screen.getPrimary().getBounds().getHeight());

        // Load the image from file
        Image backgroundImage = new Image("file:C:\\Users\\amina\\workspace\\MyDesktopPlanner\\planner.jpg");

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
