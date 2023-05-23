package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.AppData;
import model.Planner;
import model.Task;
import model.TimeSlot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.TreeSet;

import controller.TasksController;

public class ChooseTimeView extends Stage {
	private TasksController tasksplanner;
	private Task task;
    private AppData appData;
    private Planner planner;

    public ChooseTimeView(AppData appData,Task task) {
    	this.task = task;
        this.appData = appData;
        this.planner = appData.getCurrentUser().getPlanner();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(30);
        grid.setPadding(new Insets(50, 50, 50, 50));
        GridPane.setMargin(grid, new Insets(20));

        for (int i = 0; i < 3; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / 3);
            grid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 4; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / 4);
            grid.getRowConstraints().add(row);
        }

        Label title = new Label("Choose Time");
        title.setStyle("-fx-text-fill: black");
        title.setFont(Font.font("Lobster", 30));
        grid.add(title, 0, 0, 3, 1);

        Label dayLabel = new Label("Day:");
        grid.add(dayLabel, 0, 1);

        DatePicker datePicker = new DatePicker();
        LocalDate minDate = planner.getFirst_Day();
        LocalDate maxDate = planner.getLast_Day();
        datePicker.setDayCellFactory(getDatePickerDayCellFactory(minDate, maxDate));
        grid.add(datePicker, 1, 1, 2, 1);

        Label timeSlotLabel = new Label("Time Slot:");
        grid.add(timeSlotLabel, 0, 2);

        ComboBox<String> timeComboBox = new ComboBox<>();
        timeComboBox.setPromptText("Select a time");
        datePicker.valueProperty().addListener((observable, oldValue, newValue) ->
                timeComboBox.setItems(getAvailableTimeSlots(newValue)));
        grid.add(timeComboBox, 1, 2, 2, 1);
        Label blockLabel = new Label("Block Time Slot:");
        grid.add(blockLabel, 0, 3);

        CheckBox blockCheckBox = new CheckBox();
        grid.add(blockCheckBox, 1, 3,2,1);
        HBox repeatContainer = new HBox(10);
        repeatContainer.setAlignment(Pos.CENTER_LEFT);
        Label repeatLabel = new Label("Repeat:");
        TextField repeatIntervalTextField = new TextField();
        repeatIntervalTextField.setPromptText("each 1 day");
        Label forDaysLabel = new Label("for");
        TextField repeatDurationTextField = new TextField();
        repeatDurationTextField.setPromptText("1 days");

        repeatContainer.getChildren().addAll(repeatLabel, repeatIntervalTextField, forDaysLabel, repeatDurationTextField);

        grid.add(repeatContainer, 0, 4, 4, 1);

        Button planButton = new Button("Plan");
        planButton.setPrefSize(120, 40);
        planButton.setTextFill(Color.WHITE);
        planButton.setStyle("-fx-background-color: #82a156;");
        planButton.setFont(Font.font("Verdana", 15));

     // Event handler for button click
        planButton.setOnAction(event -> {
        	tasksplanner = new TasksController(appData);

            LocalDate selectedDate = datePicker.getValue();
            String selectedTimeSlot = timeComboBox.getValue();

            if (selectedDate != null && selectedTimeSlot != null) {
                // Parse the selected time slot
                LocalTime startTime = LocalTime.parse(selectedTimeSlot.split(" - ")[0]);
                LocalTime endTime = LocalTime.parse(selectedTimeSlot.split(" - ")[1]);
                boolean isBlocked = blockCheckBox.isSelected();
                // Create the TimeSlot object
                TimeSlot timeSlot = new TimeSlot(startTime, endTime);

                String repeatIntervalText = repeatIntervalTextField.getText();
                String repeatDurationText = repeatDurationTextField.getText();

                if (!repeatIntervalText.isEmpty() && !repeatDurationText.isEmpty()) {
                    int repeatInterval = Integer.parseInt(repeatIntervalText);
                    int repeatDuration = Integer.parseInt(repeatDurationText);

                    LocalDate endDate = selectedDate.plusDays(repeatDuration);
                    LocalDate currentDate = selectedDate;

                    while (!currentDate.isAfter(endDate)) {
                        tasksplanner.PlanManualy(task, currentDate, timeSlot, isBlocked);
                        currentDate = currentDate.plusDays(repeatInterval);
                    }
                } else {
                    tasksplanner.PlanManualy(task, selectedDate, timeSlot, isBlocked);
                }

                // Close the stage
                close();
            }
        });



        grid.add(planButton, 1, 5, 2, 1);


        Scene scene = new Scene(grid, 500, 300);
        setScene(scene);
    }

    private Callback<DatePicker, DateCell> getDatePickerDayCellFactory(LocalDate minDate, LocalDate maxDate) {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(minDate) || item.isAfter(maxDate));
            }
        };
    }

    private ObservableList<String> getAvailableTimeSlots(LocalDate date) {
        Set<TimeSlot> timeSlots = planner.getCalendar().getTimeByDay().get(date);
        ObservableList<String> timeSlotList = FXCollections.observableArrayList();
        if (timeSlots != null) {
            for (TimeSlot timeSlot : timeSlots) {
            	if(timeSlot.isFree()){
                String timeSlotString = timeSlot.getStartTime() + " - " + timeSlot.getEndTime();
                timeSlotList.add(timeSlotString);}
            }
        }
        return timeSlotList;
    }
}