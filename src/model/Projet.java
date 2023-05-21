package TP.model;

public class Projet {
private String nom;
private String description;
private Task[] tasks;
private Status etat;

//Constructor



//Setters&Getters
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Task[] getTaches() {
	return tasks;
}
public void setTaches(Task[] taches) {
	this.tasks = taches;
}
public Status getEtat() {
	return etat;
}
public void setEtat(Status etat) {
	this.etat = etat;
}

}
