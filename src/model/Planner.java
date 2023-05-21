package TP.model;

import java.util.*;

import TP.model.*;

import java.time.*;

public class Planner {
	
	 private Calendar calendar;
	 //private TreeSet<Task> tasks;====> dans User
	 private HashSet<Category> categories; 
	 private int minTaskPerDay;
	 private int minTimeSlotMinute=30;
	 private int felicitaions;
	 private ArrayList<Badge> badges;
	 private LocalDate first_Day;
	 private LocalDate last_Day;
	 private String name;
	 
	
	
	
	
	
	
	
   



 
 
 //constructors: 
 public Planner() {}
 public Planner(Calendar calendar,HashSet<Category> categories ) 
 {
	 this.calendar=calendar;
	 this.categories=categories;
 }
 
 
 
 public LocalDate getFirst_Day() {
	return first_Day;
}
public void setFirst_Day(LocalDate first_Day) {
	this.first_Day = first_Day;
}
public LocalDate getLast_Day() {
	return last_Day;
}
public void setLast_Day(LocalDate last_Day) {
	this.last_Day = last_Day;
}




public int getMinTimeSlotMinute() {
	return minTimeSlotMinute;
}
public void setMinTimeSlotMinute(int minTimeSlotMinute) {
	this.minTimeSlotMinute = minTimeSlotMinute;
}
public Planner(String name, LocalDate first, LocalDate last ) 
 {
	 this.name=name;
	 this.first_Day=first;
	 this.last_Day=last;
	  TreeMap<LocalDate, ArrayList<TimeSlot>> CréneauParJour= new TreeMap<LocalDate, ArrayList<TimeSlot>>();
	  Calendar calen= new Calendar (CréneauParJour);
	  this.calendar=calen;
 }
 
 
 // Getters & setters: 
 public Calendar getCalendar() {
	return calendar;
}

public void setCalendar(Calendar calendar) {
	this.calendar = calendar;
}


public HashSet<Category> getCategories() {
	return categories;
}
public void setCategories(HashSet<Category> categories) {
	this.categories = categories;
}


//affichage:

  //* les créneux libres: 
public void afficher_Calendar()
{
	 calendar.afficher();
}
 
}
