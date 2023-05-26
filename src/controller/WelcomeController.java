package controller;
import model.AppData;
import model.DataHandler;
import model.User;
import java.util.ArrayList;


//Controller class
public class WelcomeController {
 private AppData appData;
 //constructor
 public WelcomeController(AppData appData) {
     this.appData = appData;
 }

public AppData getAppData() {
	return appData;
}




// verify if the user is new or not
public boolean verifyUser(String name) {
     ArrayList<User> users = appData.getUsers();
     User user = findUser(name);

     if (user != null) {
         appData.setCurrentUser(user);
         appData.setUserSignedIn(true);
         DataHandler.save(appData);
         return false; //not their first time
     } else {
         User newUser = new User(name);
         users.add(newUser);
         appData.setCurrentUser(newUser);
         appData.setUserSignedIn(true);
         DataHandler.save(appData);
         return true; //first time
     }
 }
// look for the user in the list of users by their pseudo
 private User findUser(String name) {
     ArrayList<User> users = appData.getUsers();
     for (User user : users) {
    	 System.out.println(user.getUsername());
         if (user.getUsername().equals(name)) {
             return user;
         }
     }
     return null;
 }



}

