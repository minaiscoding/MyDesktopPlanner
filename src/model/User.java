package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private boolean loggedIn;
    private ArrayList<Task> tasks;
    private Planner planner;

    public User() {
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
        ///////
    }

    public Planner getPlanner() {
        return planner;
    }

    public void setPlanner(Planner planner) {
        this.planner = planner;
    }

    public boolean isValidUser(String username, String password) {
        // check if user exists in database
        File file = new File("users.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;    // return true if user exists and password matches, false otherwise
    }

    public boolean registerUser(String username, String password) {
        // check if user already exists in database
        File file = new File("users.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return false; // user already exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // add new user to database
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(username + "," + password + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
