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
	 public void PlanManualy(Task task,LocalDate jour ,TimeSlot timeslot,boolean blocked) {
		 if(!task.getDuration().isZero()){
		 TimeSlot[] timeSlots = timeslot.decompose(task.getDuration());

		// Accessing the data from the array
		TimeSlot oldtime = timeSlots[0];
		TimeSlot freetime = timeSlots[1];
		 oldtime.setTask(task);
		 task.setscheduled(true);
		 oldtime.setFree(false);
		 oldtime.setBlocked(blocked);

		 appData.getCurrentUser().getPlanner().getCalendar().UpdateTimeSlot(jour, oldtime);
		 appData.getCurrentUser().getPlanner().getCalendar().add_TimeSlot(jour, freetime);
		 DataHandler.save(appData);}
		 else{
			 timeslot.setTask(task);
			 task.setscheduled(true);
			 timeslot.setFree(false);
			 timeslot.setBlocked(blocked);

		 }


		}




}
