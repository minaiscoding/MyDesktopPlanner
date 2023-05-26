package view;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.AppData;
import model.*;

public class HistoryPageView {
    private AppData appData;
    private BorderPane root;

    public HistoryPageView(AppData appData) {
        this.appData = appData;
        root = new BorderPane();
    }

    public void show(Stage stage) {
        HistoryData loader = HistoryData.loadFromFile();
        ArrayList<HistoryPlanner> list = loader.getHistoryList();

     // Create the navigation bar
        NavBar navBar = new NavBar(stage, appData);

        VBox historyBox = new VBox();


        for (HistoryPlanner planner : list) {
            Label nameLabel = new Label("Name: " + planner.getName());
            Label numBadgesLabel = new Label("Number of Badges: " + planner.getNumBadges());
            Label goodLabel = new Label("Good: " + planner.getGood());
            Label veryGoodLabel = new Label("Very Good: " + planner.getVeryGood());
            Label excellentLabel = new Label("Excellent: " + planner.getExcellent());
            Label finishedTasksLabel = new Label("Finished Tasks: " + planner.getFinishedTasks());
            Label finishedProjectsLabel = new Label("Finished Projects: " + planner.getFinishedProjects());

            VBox plannerBox = new VBox(nameLabel, numBadgesLabel, goodLabel, veryGoodLabel, excellentLabel, finishedTasksLabel, finishedProjectsLabel);
            plannerBox.setSpacing(10);
            plannerBox.setPadding(new Insets(10));
            plannerBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px;");

            historyBox.getChildren().add(plannerBox);
        }



        historyBox.setPadding(new Insets(10));

        // Add the statsBox and badgesBox to the center of the BorderPane
        root.setCenter(historyBox);

        // Add the navigation bar to the left of the BorderPane
        root.setLeft(navBar);

        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}
