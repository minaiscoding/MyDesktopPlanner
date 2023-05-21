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
import java.util.TreeMap;

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
    	 TreeMap<LocalDate, ArrayList<TimeSlot>> timeByDay = new TreeMap<LocalDate, ArrayList<TimeSlot>>();

         LocalDate startDate = LocalDate.now(); // Start from today
         int numOfDays = 7; // Number of days in the week

         for (int i = 0; i < numOfDays; i++) {
             LocalDate date = startDate.plusDays(i);
             ArrayList<TimeSlot> timeSlots = new ArrayList<>();

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
         for (Entry<LocalDate, ArrayList<TimeSlot>> entry : calendar.getTimeByDay().entrySet()) {
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
    	 Category cat1 = new Category();

    	 Task_simple task1 = new Task_simple("Task 1", Duration.ofMinutes(60), Priority.HIGH, LocalDate.now(), false, Status.notRealised, cat1);
    	 taskList.add(task1);

    	 Task_simple task2 = new Task_simple("Task 2", Duration.ofMinutes(60), Priority.HIGH, LocalDate.now(), false, Status.notRealised, cat1);
    	 taskList.add(task2);

    	 Task_simple task3 = new Task_simple("Task 3", Duration.ofMinutes(60), Priority.HIGH, LocalDate.now(), false, Status.notRealised, cat1);
    	 taskList.add(task3);

    	 Task_simple task4 = new Task_simple("Task 4", Duration.ofMinutes(60), Priority.HIGH, LocalDate.now(), false, Status.notRealised, cat1);
    	 taskList.add(task4);

    	 Task_simple task5 = new Task_simple("Task 5", Duration.ofMinutes(60), Priority.HIGH, LocalDate.now(), false, Status.notRealised, cat1);
    	 taskList.add(task5);

    	 Task_simple task6 = new Task_simple("Task 6", Duration.ofMinutes(60), Priority.HIGH, LocalDate.now(), false, Status.notRealised, cat1);
    	 taskList.add(task6);

    	 Task_simple task7 = new Task_simple("Task 7", Duration.ofMinutes(60), Priority.HIGH, LocalDate.now(), false, Status.notRealised, cat1);
    	 taskList.add(task7);

    	 Task_simple task8 = new Task_simple("Task 8", Duration.ofMinutes(60), Priority.HIGH, LocalDate.now(), false, Status.notRealised, cat1);
    	 taskList.add(task8);

    	 Task_simple task9 = new Task_simple("Task 9", Duration.ofMinutes(60), Priority.HIGH, LocalDate.now(), false, Status.notRealised, cat1);
    	 taskList.add(task9);

    	 Task_simple task10 = new Task_simple("Task 10", Duration.ofMinutes(60), Priority.HIGH, LocalDate.now(), false, Status.notRealised, cat1);
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


