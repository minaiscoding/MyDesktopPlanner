

package TP.model;



public class Category {
	
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
	 
	 // reéfinition du hashcode et equals: 
	 
	   public boolean equals(Object o)
	    {
	      return ((Category)o).getType().equals(this.type);
	    }
	   public int hashCode()
	    {
	        return this.type.hashCode();
	    }
	 

}
