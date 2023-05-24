package view;

import controller.TasksController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
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
import model.Status;
import model.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TaskView {
    private TasksController controller;
    private BorderPane root;

    public TaskView(TasksController controller) {
        this.controller = controller;
        root = new BorderPane();
    }

    public void show(Stage stage) {
        ArrayList<Task> taskList = controller.getAppData().getCurrentUser().getTasks();

        // Create the navigation bar
        NavBar navBar = new NavBar(stage, controller.getAppData());

        // Create the custom table view
        CustomTableView tableView = new CustomTableView(taskList);

        // Create the title label
        Label titleLabel = new Label(controller.getAppData().getCurrentUser().getUsername() + "'s Tasks");

        titleLabel.setFont(Font.loadFont("file:Lobster-Regular.ttf", 50));
        titleLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #577133;");
        titleLabel.setPadding(new Insets(20));

        // Set the background color of the root pane
        root.setBackground(new Background(new BackgroundFill(Color.web("#CADFAD"), null, null)));

        // Create a VBox to hold the title label and table view
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(40));

        // Create the button
        Button markAsDoneButton = new Button("Mark as Done");

        // Set the action for the button
        markAsDoneButton.setOnAction(event -> {
            // Get the selected tasks from the table view
            ObservableList<Task> selectedTasks = tableView.getSelectionModel().getSelectedItems();
            controller.MarkAsDone(selectedTasks);
            tableView.refresh();
        });

        // Enable multiple selection in the table view
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Create the "Plan" button
        Button planButton = new Button("Plan");
        planButton.setOnAction(e -> {

        	ObservableList<Task> selectedTasks = tableView.getSelectionModel().getSelectedItems();
        	ArrayList<Task> SelectedTasks = new ArrayList<>(selectedTasks);
        	Planifier0 plan = new Planifier0(controller.getAppData(),SelectedTasks);

        	plan.show();
        });

        // Create the "Add a Task" button
        Button addTaskButton = new Button("Add a Task");
        addTaskButton.setOnAction(event -> {
            // Handle the action for adding a task
        	Ajout_tache addTask= new Ajout_tache(controller.getAppData());
        	tableView.refresh();
     		addTask.show();
     		tableView.refresh();
        });

        // Add the buttons to the actions box
        HBox actionsBox = new HBox();
        actionsBox.setSpacing(10);
        actionsBox.setAlignment(Pos.CENTER_LEFT);
        actionsBox.setPadding(new Insets(10));
        actionsBox.getChildren().addAll( addTaskButton,markAsDoneButton, planButton);

        // Add the title label, table view, and actions box to the VBox
        vbox.getChildren().addAll(titleLabel, tableView, actionsBox);

        // Set the VBox as the center of the BorderPane
        root.setCenter(vbox);

        // Add the navigation bar to the left of the BorderPane
        root.setLeft(navBar);

        // Create a Scene and show it on the specified Stage
        Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }


}

class TimePicker extends HBox {
    private Spinner<Integer> hourSpinner;
    private Spinner<Integer> minutesSpinner;

    public TimePicker() {
        hourSpinner = new Spinner<>(0, 23, 0);
        hourSpinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        hourSpinner.setEditable(true);

        minutesSpinner = new Spinner<>(0, 59, 0);
        minutesSpinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
        minutesSpinner.setEditable(true);

        setSpacing(5);
        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(hourSpinner, minutesSpinner);
    }

    public LocalTime getValue() {
        int hours = hourSpinner.getValue();
        int minutes = minutesSpinner.getValue();
        return LocalTime.of(hours, minutes);
    }
}
