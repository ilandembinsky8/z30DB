package lib;

public class SearchHandwriting extends Search{

	private String publisher;
	
	public SearchHandwriting(int searchID, int userID, String freeText, int fromYear, int toYear,
			String publisher) {
		super(searchID, userID, freeText, fromYear, toYear);
		this.publisher = publisher;
	}

	public String getPubilsher() {
		return this.publisher;
	}

}
