package lib;

public class Favorite {
	private int FavoriteID;
	private String BookID;
	private String PageLink;
	private String Description;
	private int PageNumber;
	public Favorite(int favoriteID, String bookID, String pageLink, String description, 
			int pageNumber) {
		super();
		FavoriteID = favoriteID;
		BookID = bookID;
		PageLink = pageLink;
		Description = description;
		PageNumber = pageNumber;
	}
	public int getFavoriteID() {
		return FavoriteID;
	}
	public void setFavoriteID(int favoriteID) {
		FavoriteID = favoriteID;
	}
	public String getBookID() {
		return BookID;
	}
	public void setBookID(String bookID) {
		BookID = bookID;
	}
	public String getPageLink() {
		return PageLink;
	}
	public void setPageLink(String pageLink) {
		PageLink = pageLink;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

	public int getPageNumber() {
		return PageNumber;
	}
	public void setPageNumber(int pageNumber) {
		PageNumber = pageNumber;
	}
	
	
	
	

}
