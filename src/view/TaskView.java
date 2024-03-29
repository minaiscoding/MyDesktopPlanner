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
import java.util.ArrayList;

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
            // Create a new stage for the plan options window
            Stage planOptionsStage = new Stage();
            planOptionsStage.initStyle(StageStyle.UTILITY);

            // Create the labels and buttons for manual and automatic planning options
            Label messageLabel = new Label("How do you want to plan the selected tasks?");
            Button manualButton = new Button("Manual");
            Button automaticButton = new Button("Automatic");

            // Set the actions for the manual and automatic buttons
            manualButton.setOnAction(event -> {
            	 ObservableList<Task> selectedTasks = tableView.getSelectionModel().getSelectedItems();

                handleManualPlanning(selectedTasks);
                planOptionsStage.close();
             // Refresh the table view
                tableView.refresh();
            });

            automaticButton.setOnAction(event -> {
                handleAutomaticPlanning();
                planOptionsStage.close();
            });

            // Create a VBox to hold the labels and buttons
            VBox planOptionsVBox = new VBox(10);
            planOptionsVBox.setPadding(new Insets(10));
            planOptionsVBox.getChildren().addAll(messageLabel, manualButton, automaticButton);

            // Create a scene for the plan options window
            Scene planOptionsScene = new Scene(planOptionsVBox);
            planOptionsStage.setScene(planOptionsScene);
            planOptionsStage.show();
        });

        // Add the button to the VBox
        vbox.getChildren().addAll(titleLabel, tableView, markAsDoneButton,planButton);

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

    private void handleManualPlanning(ObservableList<Task> selectedTasks) {
        // Iterate over the selected tasks
        for (Task task : selectedTasks) {
            // Create a new stage for each task's manual planning window
            Stage manualPlanningStage = new Stage();
            manualPlanningStage.initStyle(StageStyle.UTILITY);

            // Create labels and input fields for start day and start time
            Label startDayLabel = new Label("Start Day:");
            DatePicker startDayPicker = new DatePicker();

            Label startTimeLabel = new Label("Start Time:");
            TimePicker startTimePicker = new TimePicker();

            // Create the plan button
            Button planButton = new Button("Plan");
            planButton.setOnAction(event -> {
                // Get the start day and start time from the input fields
                LocalDate startDay = startDayPicker.getValue();
                LocalTime startTime = startTimePicker.getValue();

                // Plan the task with the specified start day and start time
                controller.PlanManualy(task, startDay, startTime);



                // Close the manual planning window
                manualPlanningStage.close();
            });

            // Create a VBox to hold the labels, input fields, and button
            VBox manualPlanningVBox = new VBox(10);
            manualPlanningVBox.setPadding(new Insets(10));
            manualPlanningVBox.getChildren().addAll(startDayLabel, startDayPicker, startTimeLabel, startTimePicker, planButton);

            // Create a scene for the manual planning window
            Scene manualPlanningScene = new Scene(manualPlanningVBox);
            manualPlanningStage.setScene(manualPlanningScene);
            manualPlanningStage.showAndWait();
        }
    }


    private void handleAutomaticPlanning() {
        // Implementation for automatic planning
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Automatic Planning");
        alert.setHeaderText(null);
        alert.setContentText("You have selected Automatic planning.");
        alert.showAndWait();
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

