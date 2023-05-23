package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

public class User implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 8312128879020914324L;
	private String username;
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Planner planner = new Planner();

    public User() {
        this.username = "";
    }
    public User(String name)
    {this.username = name;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> taskList) {
        this.tasks = taskList;
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
}
