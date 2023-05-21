package model;

import javafx.application.Application;
import javafx.stage.Stage;
import view.*;
import controller.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.sun.javafx.collections.MappingChange.Map;


//Main class
public class MyDesktopPlanner extends Application {

 public static void main(String[] args) {
     launch(args);
 }

 @Override
 public void start(Stage primaryStage) {
     AppData appData = new AppData();
     appData = DataHandler.load();
     if(appData.isUserSignedIn()){
    	 appData.getCurrentUser().getPlanner().setCalendar(new Calendar());
    	 Calendar calendar = appData.getCurrentUser().getPlanner().getCalendar();
    	 HashMap<LocalDate, List<TimeSlot>> timeByDay = new HashMap<LocalDate, List<TimeSlot>>();

         LocalDate startDate = LocalDate.now(); // Start from today
         int numOfDays = 7; // Number of days in the week

         for (int i = 0; i < numOfDays; i++) {
             LocalDate date = startDate.plusDays(i);
             List<TimeSlot> timeSlots = new ArrayList<>();

             // Create five time slots for each day
             LocalTime startTime = LocalTime.of(9, 0); // Start time
             int slotDurationInMinutes = 60; // Duration of each time slot in minutes

             for (int j = 0; j < 5; j++) {
                 LocalTime endTime = startTime.plusMinutes(slotDurationInMinutes);
                 TimeSlot timeSlot = new TimeSlot(startTime, endTime);
                 timeSlots.add(timeSlot);
                 startTime = endTime; // Set the start time of the next time slot
             }

             timeByDay.put(date, timeSlots);
         }


         calendar.setTimeByDay(timeByDay);
         DataHandler.save(appData);

         // Print the calendar to verify the initialization
         for (Entry<LocalDate, List<TimeSlot>> entry : calendar.getTimeByDay().entrySet()) {
             LocalDate date = entry.getKey();
             List<TimeSlot> timeSlots = entry.getValue();
             System.out.println("Date: " + date);
             for (TimeSlot timeSlot : timeSlots) {
                 System.out.println("Start Time: " + timeSlot.getStartTime());
                 System.out.println("End Time: " + timeSlot.getEndTime());
                 System.out.println();
             }
         }

    	 ArrayList<Task> taskList = new ArrayList<>();

    	 Task task1 = new Task("Task 1", Duration.ofMinutes(60), PrioTask.HIGH, new Date(), false, Status.IN_PROGRESS, "Category 1");
    	 taskList.add(task1);

    	 Task task2 = new Task("Task 2", Duration.ofMinutes(120), PrioTask.LOW, new Date(), true, Status.TODO, "Category 2");
    	 taskList.add(task2);

    	 Task task3 = new Task("Task 3", Duration.ofMinutes(90), PrioTask.MEDIUM, new Date(), false, Status.DONE, "Category 1");
    	 taskList.add(task3);

    	 Task task4 = new Task("Task 4", Duration.ofMinutes(80), PrioTask.HIGH, new Date(), false, Status.IN_PROGRESS, "Category 2");
    	 taskList.add(task4);

    	 Task task5 = new Task("Task 5", Duration.ofMinutes(150), PrioTask.LOW, new Date(), true, Status.TODO, "Category 1");
    	 taskList.add(task5);

    	 Task task6 = new Task("Task 6", Duration.ofMinutes(70), PrioTask.MEDIUM, new Date(), false, Status.DONE, "Category 2");
    	 taskList.add(task6);

    	 Task task7 = new Task("Task 7", Duration.ofMinutes(100), PrioTask.HIGH, new Date(), false, Status.IN_PROGRESS, "Category 1");
    	 taskList.add(task7);

    	 Task task8 = new Task("Task 8", Duration.ofMinutes(110), PrioTask.LOW, new Date(), true, Status.TODO, "Category 2");
    	 taskList.add(task8);

    	 Task task9 = new Task("Task 9", Duration.ofMinutes(130), PrioTask.MEDIUM, new Date(), false, Status.DONE, "Category 1");
    	 taskList.add(task9);

    	 Task task10 = new Task("Task 10", Duration.ofMinutes(90), PrioTask.HIGH, new Date(), false, Status.IN_PROGRESS, "Category 2");
    	 taskList.add(task10);


         // Assuming appData is an instance of your application data class
         appData.getCurrentUser().setTasks(taskList);

         // Assuming DataHandler is your data handling class
         DataHandler.save(appData);
    	 TasksController Tcontroller = new TasksController(appData);
    	 TaskView tasks = new TaskView(Tcontroller);
    	 tasks.show(primaryStage);
     }
     else{
    	 WelcomePageView welcomePage = new WelcomePageView();
         WelcomeController welcomeController = new WelcomeController(appData);
         welcomePage.setController(welcomeController);
         welcomePage.show(primaryStage);

     }



 }
}


