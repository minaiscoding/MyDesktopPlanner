package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Statistiques implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1617537656487753444L;


    // Other class members and methods

    public static VBox todayTasksStatus(Calendar calendar) {
        TreeMap<LocalDate, TreeSet<TimeSlot>> timeByDay = calendar.getTimeByDay();
        LocalDate today = LocalDate.now();
            if (timeByDay == null) {
                Label noTasksLabel = new Label("No tasks found for today.");
                VBox vbox = new VBox(noTasksLabel);
                vbox.setPadding(new Insets(10));
                return vbox;
            }

            TreeSet<TimeSlot> timeSlots = timeByDay.get(today);
            if (timeSlots == null || timeSlots.isEmpty()) {
                Label noTasksLabel = new Label("No tasks found for today.");
                VBox vbox = new VBox(noTasksLabel);
                vbox.setPadding(new Insets(10));
                return vbox;
            }


        int totalTasks = timeSlots.size();
        int completedTasks = 0;
        for (TimeSlot timeSlot : timeSlots) {
        	if(timeSlot.getTask() != null){
            if (timeSlot.getTask().getStatus()==Status.completed) {
                completedTasks++;
            }}
        }

        Label titleLabel = new Label("Today's Tasks Status:");
        titleLabel.setFont(Font.font("Lobster", 20)); // Set font and size
        titleLabel.setTextFill(Color.DARKGREEN); // Set text color

        Label totalLabel = new Label("Total Tasks: " + totalTasks);
        totalLabel.setFont(Font.font(16)); // Set font and size

        Label completedLabel = new Label("Completed Tasks: " + completedTasks);
        completedLabel.setFont(Font.font(16)); // Set font and size

        VBox vbox = new VBox(titleLabel, totalLabel, completedLabel);
        vbox.setPadding(new Insets(10));
        return vbox;
    }
    public static VBox projectTasksStatus(Projet project) {
        ArrayList<Task> tasks = project.getTaches();

        if (tasks.isEmpty()) {
            Label noTasksLabel = new Label("No tasks found for the project.");
            VBox vbox = new VBox(noTasksLabel);
            vbox.setPadding(new Insets(10));
            return vbox;
        }

        int totalTasks = tasks.size();
        int completedTasks = 0;
        int pendingTasks = 0;

        for (Task task : tasks) {
            if (task.getStatus()==Status.completed){
                completedTasks++;
            } else {
                pendingTasks++;
            }
        }

        Label titleLabel = new Label(project.getNom()+"'s Tasks Status:");
        titleLabel.setFont(Font.font("Lobster", 20)); // Set font and size
        titleLabel.setTextFill(Color.DARKGREEN); // Set text color

        Label totalLabel = new Label("Total Tasks: " + totalTasks);
        totalLabel.setFont(Font.font(16)); // Set font and size

        Label completedLabel = new Label("Completed Tasks: " + completedTasks);
        completedLabel.setFont(Font.font(16)); // Set font and size

        Label pendingLabel = new Label("Pending Tasks: " + pendingTasks);
        pendingLabel.setFont(Font.font(16)); // Set font and size

        VBox vbox = new VBox(titleLabel, totalLabel, completedLabel, pendingLabel);
        vbox.setPadding(new Insets(10));
        return vbox;
    }}