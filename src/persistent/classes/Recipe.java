package persistent.classes;

import java.io.File;
import java.io.FileInputStream;


public class Recipe  {
	int ID;
	String autor;
	String name;
	String subtitle;
	int time;
	int cooked;
	int numberOfRatings;
	Float averageRating;
	String preparation;
	
	String category;
	byte[] foto;
	
	
	
	

	
	public Recipe(){
		
	}
	
	public Recipe(String autor, String name, String subtitle, int time,
			String preparation) {
		super();
		this.autor = autor;
		this.name = name;
		this.subtitle = subtitle;
		this.time = time;	
		this.preparation = preparation;
		this.cooked = 0;
		///this.foto = foto;
	}
	
	public Recipe(String autor, String name, String subtitle, int time,
			String preparation, File f) {
		super();
		this.autor = autor;
		this.name = name;
		this.subtitle = subtitle;
		this.time = time;	
		this.preparation = preparation;
		foto = new byte[(int)f.length()];
		this.cooked = 0;
		
		try {
			FileInputStream fileInputStream = new FileInputStream(f);
			fileInputStream.read(foto);
			fileInputStream.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getCooked() {
		return cooked;
	}
	public void setCooked(int cooked) {
		this.cooked = cooked;
	}
	public int getNumberOfRatings() {
		return numberOfRatings;
	}
	public void setNumberOfRatings(int numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}
	public float getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(float averageRating) {
		this.averageRating = averageRating;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	public String toString(){
		return this.name + " " + this.subtitle;
	}
	
}
