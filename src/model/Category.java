package model;
import java.util.*;

public class Category {
    private String type;
    private String color;
    private ArrayList<Task> taches;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setTaches(ArrayList<Task> taches) {
        this.taches = taches;
    }

    public ArrayList<Task> getTaches() {
        return taches;
    }
}
