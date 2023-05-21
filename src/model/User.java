package TP.model;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;




public class User {
	
	//les attributs: 
    private String username;
    private String password;
    private boolean loggedIn;
    private TreeSet<Task> tasks;
    private Planner planner;
    
    
    //les methodes: 
    
   //constructors: 

    public User() {
        this.username = "";
        this.password = "";
        this.loggedIn = false;
        tasks= new TreeSet<Task>();
    }
    
    
  //Getters && Setters: 

    public User(Planner planner2) {
		this.planner=planner2	;
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

    public TreeSet<Task> getTasks() {
        return tasks;
    }

    public void setTasks(TreeSet<Task>tasks) {
        this.tasks = tasks;
        
    }

    public Planner getPlanner() {
        return planner;
    }

    public void setPlanner(Planner planner) {
        this.planner = planner;
    }
    
    
    /// Affichage: 

	public void afficher_créneau()
	{
		planner.afficher_Calendar();
	}
	
	public void afficher_Tasks()
	{
		Iterator<Task>  it = tasks.iterator();
        while (it.hasNext())
        {
            it.next().afficher();
        }
		
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






















