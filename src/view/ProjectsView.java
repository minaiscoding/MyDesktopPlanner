package view;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AppData;
import model.DataHandler;
import model.Projet;
import model.Task;

public class ProjectsView {
	private AppData appData;
	private BorderPane root;
	public ProjectsView(AppData appData)
	{
		this.appData = appData;
		root = new BorderPane();
	}
	public void show(Stage stage) {
	    ArrayList<Projet> projectsList = appData.getCurrentUser().getProjects();

	    // Create the navigation bar
	    NavBar navBar = new NavBar(stage, appData);

	    VBox projectBox = new VBox(20);
	    projectBox.setAlignment(Pos.CENTER);


	    for (Projet project : projectsList) {
	        // Create a label for the project name
	        Label projectNameLabel = new Label(project.getNom());
	        projectNameLabel.setStyle("-fx-font-size: 18px;");

	        // Create a CustomTableView for the task list of each project
	        if (project.getTaches() == null) {
	            project.setTaches(new ArrayList<Task>());
	        }
	        CustomTableView tableView = new CustomTableView(project.getTaches());
	     // Create a button to add a new task to the project
	        Button addTaskButton = new Button("Add Task to this Project");
	        addTaskButton.setOnAction(event -> {
	        	AddTaskToProject add = new AddTaskToProject(appData,project);
	        	add.show();

	            });


	        // Add the project name label, the CustomTableView, and the addTaskButton to the projectBox
	        projectBox.getChildren().addAll(projectNameLabel, tableView, addTaskButton);
	    }

	    // Set the background color of the root pane
	    root.setBackground(new Background(new BackgroundFill(Color.web("#CADFAD"), null, null)));

	    // Add the projectBox to the center of the BorderPane
	    root.setCenter(projectBox);

	    // Add the navigation bar to the left of the BorderPane
	    root.setLeft(navBar);

	    // Create the button to add a new project
	    Button addProjectButton = new Button("Add Project");
	    addProjectButton.setOnAction(event -> {
	        // Create a new stage for entering project details
	        Stage addProjectStage = new Stage();
	        addProjectStage.initStyle(StageStyle.UTILITY);

	        // Create labels and text fields for project name and description
	        Label nameLabel = new Label("Name:");
	        TextField nameTextField = new TextField();
	        Label descriptionLabel = new Label("Description:");
	        TextField descriptionTextField = new TextField();

	        // Create the save button
	        Button saveButton = new Button("Save");
	        saveButton.setOnAction(e -> {
	            // Get the project name and description from the text fields
	            String projectName = nameTextField.getText();
	            String projectDescription = descriptionTextField.getText();

	            // Create a new project with the entered name and description
	            Projet newProject = new Projet(projectName, projectDescription);

	            // Add the new project to the projects list
	            projectsList.add(newProject);
	            DataHandler.save(appData);

	            // Close the add project window
	            addProjectStage.close();

	            // Refresh the ProjectsView
	            show(stage);
	        });

	        // Create a VBox to hold the labels, text fields, and save button
	        VBox addProjectVBox = new VBox(10);
	        addProjectVBox.setPadding(new Insets(10));
	        addProjectVBox.getChildren().addAll(nameLabel, nameTextField, descriptionLabel, descriptionTextField, saveButton);

	        // Create a scene for the add project window
	        Scene addProjectScene = new Scene(addProjectVBox);
	        addProjectStage.setScene(addProjectScene);
	        addProjectStage.showAndWait();
	    });

	    // Add the addProjectButton to the top of the BorderPane
	    root.setTop(addProjectButton);

	    Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
	    stage.setScene(scene);
	    stage.setMaximized(true);
	    stage.show();
	}




}
