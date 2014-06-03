package persistent.classes;


public class Recipe  {
	int ID;
	String autor;
	String name;
	String subtitle;
	int time;
	int cooked;
	int numberOfRatings;
	float averageRating;
	String preparation;
	
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
		///this.foto = foto;
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
	
	public String toString(){
		return this.name + " " + this.subtitle;
	}
	
}
