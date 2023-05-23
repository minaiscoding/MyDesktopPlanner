package controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.AppData;
import model.Calendar;
import model.DataHandler;
import model.Status;
import model.Task;
import model.TimeSlot;

public class TasksController {
	 private AppData appData;

	 public AppData getAppData() {
	 	return appData;
	 }
	 public TasksController(AppData appdata)
	 {
		 this.appData = appdata;
	 }
	 public void MarkAsDone( ObservableList<Task> selectedTasks){


	            // Mark each selected task as done
	            for (Task task : selectedTasks) {

	                task.setStatus(Status.completed);
	                appData.getCurrentUser().getPlanner().IncTasksDone();

	                DataHandler.save(appData);
	            }


	 }
	 public void PlanManualy(Task task, LocalDate startDay, LocalTime startTime) {

		}




}
