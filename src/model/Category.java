package model;
import java.io.Serializable;
import java.util.*;

public class Category implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = -5901258001893370970L;
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
