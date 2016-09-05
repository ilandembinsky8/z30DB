package lib;

public class SearchBook extends Search{

	private String author;
	
	public SearchBook(int searchID, int userID, String freeText, int fromYear, int toYear, String author) {
		super(searchID, userID, freeText, fromYear, toYear);
		this.author=author;
	}

	public String getAuthor() {
		return author;
	}


}
