package model;

import javafx.application.Application;
import javafx.stage.Stage;
import view.*;
import controller.*;

import java.util.ArrayList;

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
     WelcomePageView welcomePage = new WelcomePageView();
     WelcomeController welcomeController = new WelcomeController(appData);

     welcomePage.setController(welcomeController);
     welcomePage.show(primaryStage);
 }
}


