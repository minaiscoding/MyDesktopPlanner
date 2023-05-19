package controller;
import model.AppData;
import model.User;
import java.util.ArrayList;


//Controller class
public class WelcomeController {
 private AppData appData;

 public WelcomeController(AppData appData) {
     this.appData = appData;
 }



public void verifyUser(String name) {
     ArrayList<User> users = appData.getUsers();
     User user = findUser(name);

     if (user != null) {
         appData.setCurrentUser(user);
     } else {
         User newUser = new User(name);
         users.add(newUser);
         appData.setCurrentUser(newUser);
     }
 }

 private User findUser(String name) {
     ArrayList<User> users = appData.getUsers();
     for (User user : users) {
         if (user.getUsername().equals(name)) {
             return user;
         }
     }
     return null;
 }
}

