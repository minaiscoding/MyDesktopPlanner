package view;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AppData;
import model.HistoryData;
import model.HistoryPlanner;
import model.Statistiques;

public class SettingsStage extends Stage {
	private AppData appData;

    public SettingsStage(AppData appData) {
    	this.appData = appData;
        setTitle("Settings");
        setResizable(false);

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        // Create the title label
        Label titleLabel = new Label("Settings");
        titleLabel.setStyle("-fx-font-size: 24px;");

        // Create buttons
        Button changeMinTasksButton = new Button("Change minimal number of tasks");
        Button addCategoryButton = new Button("Add a new Category");
        Button changeMinDurationButton = new Button("Change the minimal duration of timeslots");
        Button saveAndStartButton = new Button("Save and start a new planner");
        saveAndStartButton.setOnAction(event -> {
        	HistoryPlanner tosave = new HistoryPlanner();
        	Statistiques.createHistoryPlanner(tosave, appData);
        	HistoryData saver = new HistoryData();
        	saver = HistoryData.loadFromFile();
        	saver.addHistoryPlanner(tosave);
        	saver.saveToFile();
        	Add_Planner newPlanner = new Add_Planner(appData);
        	newPlanner.show();

        });

        // Add button actions here
     // Handle "Change minimal duration of timeslot" button click
        changeMinDurationButton.setOnAction(event -> {
            // Create a new stage for entering the minimal duration of timeslot
            Stage changeDurationStage = new Stage();
            changeDurationStage.initModality(Modality.APPLICATION_MODAL);
            changeDurationStage.setResizable(false);

            VBox changeDurationRoot = new VBox(20);
            changeDurationRoot.setAlignment(Pos.CENTER);

            // Create the label and text field for entering the duration
            Label durationLabel = new Label("Enter minimal duration of timeslot (in minutes):");
            TextField durationTextField = new TextField();
            durationTextField.setPromptText("Enter a number");

            // Create the save button
            Button saveButton = new Button("Save");
            saveButton.setOnAction(saveEvent -> {
                String durationText = durationTextField.getText();
                if (!durationText.isEmpty()) {
                	appData.getCurrentUser().getPlanner().setMinTaskPerDay(Integer.parseInt(durationText));
                }
                changeDurationStage.close();
            });

            changeDurationRoot.getChildren().addAll(durationLabel, durationTextField, saveButton);
            Scene changeDurationScene = new Scene(changeDurationRoot, 350, 150);
            changeDurationStage.setScene(changeDurationScene);
            changeDurationStage.show();
        });
     // Handle "Change minimal number of tasks" button click
        changeMinTasksButton.setOnAction(event -> {
            // Create a new stage for entering the minimal number of tasks
            Stage changeTasksStage = new Stage();
            changeTasksStage.initModality(Modality.APPLICATION_MODAL);
            changeTasksStage.setResizable(false);

            VBox changeTasksRoot = new VBox(20);
            changeTasksRoot.setAlignment(Pos.CENTER);

            // Create the label and text field for entering the number of tasks
            Label tasksLabel = new Label("Enter minimal number of tasks:");
            TextField tasksTextField = new TextField();
            tasksTextField.setPromptText("Enter a number");

            // Create the save button
            Button saveButton = new Button("Save");
            saveButton.setOnAction(saveEvent -> {
                String tasksText = tasksTextField.getText();
                if (!tasksText.isEmpty()) {
                    appData.getCurrentUser().getPlanner().setMinTaskPerDay(Integer.parseInt(tasksText));
                }
                changeTasksStage.close();
            });

            changeTasksRoot.getChildren().addAll(tasksLabel, tasksTextField, saveButton);
            Scene changeTasksScene = new Scene(changeTasksRoot, 300, 150);
            changeTasksStage.setScene(changeTasksScene);
            changeTasksStage.show();
        });

        root.getChildren().addAll(titleLabel, changeMinTasksButton, addCategoryButton,
                changeMinDurationButton, saveAndStartButton);

        Scene scene = new Scene(root, 400, 300);
        setScene(scene);
    }

}
