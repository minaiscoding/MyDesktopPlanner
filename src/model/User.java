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
    private ArrayList<Projet> projects = new ArrayList<Projet>();
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

    public ArrayList<Projet> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Projet> projectList) {
        this.projects = projectList;
    }
}
