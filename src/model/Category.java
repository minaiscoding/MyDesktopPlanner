package model;
import java.io.Serializable;
import java.util.*;

public class Category implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = -5901258001893370970L;
	// les attributs:
		 private String type;
		 private String color;



		 // les methodes:

		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}




		 //constructors:
		 public Category() {}
		 public Category(String type)
		 {
			 this.type=type;
		 }

		  public Category(String type, String color) {
			   this.type=type;
			   this.color=color;

		}

		 // re�finition du hashcode et equals:

		   public boolean equals(Object o)
		    {
		      return ((Category)o).getType().equals(this.type);
		    }
		   public int hashCode()
		    {
		        return this.type.hashCode();
		    }
		   @Override
		   public String toString(){
			   return this.type;
		   }
}
/*

public class Category {




}*/