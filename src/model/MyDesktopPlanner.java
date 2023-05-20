package model;

import javafx.application.Application;
import javafx.stage.Stage;
import view.*;
import controller.*;

import java.util.ArrayList;
import java.util.Date;

/*public class MyDesktopPlanner extends Application {
    private User userModel;

    @Override
    public void start(Stage primaryStage) {
        // Create a User object with some tasks
        User user = new User();
        user.setUsername("Amina");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Make dinner", 60, PrioTask.MEDIUM, null, false, Status.TODO, "Category 1"));
        tasks.add(new Task("Task 2", 120, PrioTask.HIGH, null, false, Status.TODO, "Category 2"));
        tasks.add(new Task("Task 3", 30, PrioTask.LOW, null, false, Status.TODO, "Category 3"));
        user.setTasks(tasks);

        // Create the TaskView and show it
        /*TaskView taskView = new TaskView(user);
        taskView.show(primaryStage);
        WelcomePageView welcome = new WelcomePageView();
        welcome.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
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
    	ArrayList<Task> tasksl = new ArrayList<Task>();
    	 Task task1 = new Task("Task 1", 60, PrioTask.HIGH, new Date(), false, Status.IN_PROGRESS, "Category 1");
         tasksl.add(task1);
         
         Task task2 = new Task("Task 2", 120, PrioTask.LOW, new Date(), true, Status.TODO, "Category 2");
         tasksl.add(task2);
         
         Task task3 = new Task("Task 3", 90, PrioTask.MEDIUM, new Date(), false, Status.DONE, "Category 1");
         tasksl.add(task3);
         appData.getCurrentUser().setTasks(tasksl);
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


