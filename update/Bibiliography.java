package lib;

import java.util.ArrayList;

public class Bibiliography {
	private static int idCounter=0;
	private int bibiliographyID;
	private ArrayList<String> favorites;
	private String bilbilographyName;
	
	public Bibiliography() {
		super();
		this.bibiliographyID = idCounter++;
	}
	
	public int getBibiliographyID() {
		return bibiliographyID;
	}
	
	public void setBibiliographyID(int bibiliographyID) {
		this.bibiliographyID = bibiliographyID;
	}
	public ArrayList<String> getFavorites() {
		return favorites;
	}
	public void setFavorites(ArrayList<String> favorites) {
		this.favorites = favorites;
	}
	public String getBilbilographyName() {
		return bilbilographyName;
	}
	public void setBilbilographyName(String bilbilographyName) {
		this.bilbilographyName = bilbilographyName;
	}
	
	
	
}
