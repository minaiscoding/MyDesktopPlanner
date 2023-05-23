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
import model.Statistiques;
import model.Task;

public class StatisticView {
	private AppData appData;
	private BorderPane root;
	public StatisticView(AppData appData)
	{
		this.appData = appData;
		root = new BorderPane();
	}
	public void show(Stage stage) {
	    ArrayList<Projet> projectsList = appData.getCurrentUser().getProjects();

	    // Create the navigation bar
	    NavBar navBar = new NavBar(stage, appData);

	    VBox statsBox = new VBox();
	    statsBox.getChildren().add(Statistiques.todayTasksStatus(appData.getCurrentUser().getPlanner().getCalendar()));

	    for (Projet project : projectsList) {
	        statsBox.getChildren().add(Statistiques.projectTasksStatus(project));
	    }

	    root.setBackground(new Background(new BackgroundFill(Color.web("#CADFAD"), null, null)));

	    // Add the statsBox to the center of the BorderPane
	    root.setCenter(statsBox);

	    // Add the navigation bar to the left of the BorderPane
	    root.setLeft(navBar);

	    Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
	    stage.setScene(scene);
	    stage.setMaximized(true);
	    stage.show();
	}




}
