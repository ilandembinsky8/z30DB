package lib;

public class Search {
	private int searchID;
	private int userID;
	private String freeText;
	private int fromYear;
	private int toYear;
	
	public int getSearchID() {
		return searchID;
	}
	
	public int getUserID() {
		return userID;
	}

	public String getFreeText() {
		return freeText;
	}

	public int getFromYear() {
		return fromYear;
	}

	public int getToYear() {
		return toYear;
	}

	public Search(int searchID, int userID, String freeText, int fromYear, int toYear) {
		super();
		this.searchID = searchID;
		this.userID = userID;
		this.freeText = freeText;
		this.fromYear = fromYear;
		this.toYear = toYear;
	}
	
}
