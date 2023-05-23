package view;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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

        root.getChildren().addAll(titleLabel, changeMinTasksButton, addCategoryButton,
                changeMinDurationButton, saveAndStartButton);

        Scene scene = new Scene(root, 400, 300);
        setScene(scene);
    }

}
