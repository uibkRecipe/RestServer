package persistent.classes;

import java.util.ArrayList;
import java.util.List;

public class Rezept {
	private int rez_id;
	private String autor;
	private String name;
	private String zubereitung;
	private List<Zutaten> zutaten;
	
	public Rezept(String autor, String name, String zubereitung, List<Zutaten> zutaten) {
		this.autor = autor;
		this.name = name;
		this.zubereitung = zubereitung;
		this.zutaten = zutaten;
	}

	public void setRez_id(int rez_id) {
		this.rez_id = rez_id;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setZubereitung(String zubereitung) {
		this.zubereitung = zubereitung;
	}

	public void setZutaten(List<Zutaten> zutaten) {
		this.zutaten = zutaten;
	}
	
	
	
}
