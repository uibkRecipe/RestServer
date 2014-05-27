package persistent.classes;

public class Ort {
	private int OrtId;
	private String OrtName;
	private int PLZ;
	private String Land;
	
	public Ort(){
		
	}
	
	public Ort(String ortName, int pLZ, String land) {
		super();
		OrtName = ortName;
		PLZ = pLZ;
		Land = land;
	}
	public void setOrtId(int ortId) {
		OrtId = ortId;
	}
	public void setOrtName(String ortName) {
		OrtName = ortName;
	}
	public void setPLZ(int pLZ) {
		PLZ = pLZ;
	}
	public void setLand(String land) {
		Land = land;
	}
	public int getOrtId() {
		return OrtId;
	}
	public String getOrtName() {
		return OrtName;
	}
	public int getPLZ() {
		return PLZ;
	}
	public String getLand() {
		return Land;
	}
	public String toString(){
		return getOrtName();
	}
	
}
