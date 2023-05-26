package model;

import javafx.application.Application;
import javafx.stage.Stage;
import view.*;
import controller.*;

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
    	 HomePageView home = new HomePageView(appData);
	     home.show(primaryStage);
     }
     else{
    	 WelcomePageView welcomePage = new WelcomePageView();
         WelcomeController welcomeController = new WelcomeController(appData);
         welcomePage.setController(welcomeController);
         welcomePage.show(primaryStage);

     }



 }
}


