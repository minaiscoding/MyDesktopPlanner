package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Projet implements Serializable {
    private static final long serialVersionUID = 8613010725643629898L;
    private String nom;
    private String description;
    private ArrayList<Task> taches;

    public Projet(String projectName, String projectDescription) {
        this.nom = projectName;
        this.description = projectDescription;
        this.taches = new ArrayList<>();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTaches(ArrayList<Task> taches) {
        this.taches = taches;
    }

    public ArrayList<Task> getTaches() {
        return taches;
    }

    public boolean finished() {
        for (Task task : taches) {
            if (task.getStatus()!= Status.completed) {
                return false;
            }
        }
        return true;
    }
}
