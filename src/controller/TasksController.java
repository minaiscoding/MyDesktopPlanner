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

	                task.setStatus(Status.DONE);
	                appData.getCurrentUser().getPlanner().IncTasksDone();

	                DataHandler.save(appData);
	            }


	 }
	 public void PlanManualy(Task task, LocalDate startDay, LocalTime startTime) {
		    Calendar calendar = appData.getCurrentUser().getPlanner().getCalendar();

		    List<TimeSlot> timeSlots = calendar.getTimeByDay().get(startDay);

		    // Check if time slots exist for the specified start date
		    if (timeSlots != null) {
		        // Find a suitable time slot for the task
		        for (TimeSlot timeSlot : timeSlots) {
		            if (timeSlot.getStartTime().isBefore(startTime) || timeSlot.getStartTime().equals(startTime)) {
		                // Calculate the duration of the time slot
		                Duration timeSlotDuration = Duration.between(timeSlot.getStartTime(), timeSlot.getEndTime());

		                // Calculate the duration of the task
		                Duration taskDuration = task.getDuration();

		                if (timeSlotDuration.compareTo(taskDuration) >= 0) {
		                    // Plan the task in the current time slot
		                    timeSlot.setTache(task);
		                    timeSlot.setFree(false);

		                    // Calculate the remaining duration after planning the task
		                    Duration remainingDuration = timeSlotDuration.minus(taskDuration);

		                    // Check if there is remaining duration to create a new time slot
		                    if (remainingDuration.toMinutes() > 0) {
		                        // Calculate the start time of the new time slot
		                        LocalTime newSlotStartTime = timeSlot.getEndTime().minusHours(remainingDuration.toHours());

		                        // Create a new time slot with the remaining duration
		                        TimeSlot newTimeSlot = new TimeSlot(newSlotStartTime, timeSlot.getEndTime());
		                        newTimeSlot.setFree(true);

		                        // Insert the new time slot after the current time slot
		                        int index = timeSlots.indexOf(timeSlot);
		                        timeSlots.add(index + 1, newTimeSlot);
		                    }

		                    // Exit the method after planning the task
		                    return;
		                }
		            }
		        }
		    }

		    // Handle the case when no suitable time slot is found
		    Alert alert = new Alert(AlertType.INFORMATION);
		    alert.setTitle("Time Slot Not Found");
		    alert.setHeaderText(null);
		    alert.setContentText("No suitable time slot found for the task.");
		    alert.showAndWait();
		}




}
