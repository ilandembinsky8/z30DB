package lib;

public class Favorite {
	private TypeOfResource type;
	private String BookID;
	private String PageLink;
	private String Description;
	private int PageNumber;

	public Favorite(TypeOfResource type, String bookID, String pageLink, String description, int pageNumber) {
		super();
		BookID = bookID;
		this.type = type;
		PageLink = pageLink;
		Description = description;
		PageNumber = pageNumber;
	}

	public Favorite(String bookID, String pageLink, String description, int pageNumber) {
		super();
		BookID = bookID;
		PageLink = pageLink;
		Description = description;
		PageNumber = pageNumber;
	}

	public TypeOfResource getType() {
		return type;
	}

	public void setType(TypeOfResource type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "Favorite [ BookID=" + BookID + ", PageLink=" + PageLink + ", Description=" + Description
				+ ", PageNumber=" + PageNumber + "]";
	}

}
