package model;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private boolean loggedIn;
    private ArrayList<Task> tasks;
    private Planner planner;
    public User(){
	this.username = "";
    this.password = "";
    this.loggedIn = false;
}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Planner getPlanner() {
        return planner;
    }

    public void setPlanner(Planner planner) {
        this.planner = planner;
    }
    public boolean isValidUser(String username, String password) {
        // check if user exists in database
    return false;    // return true if user exists and password matches, false otherwise
    }


    public boolean registerUser(String username, String password) {
        // add new user to database
        // return true if registration successful, false otherwise
    	return false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
