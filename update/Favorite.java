package lib;

public class Favorite {
	private TypeOfResource type;
	private String bookID;
	private String pageLink;
	private String description;
	private int pageNumber;
	private String title;
	private String author;
	private String publisher;
	private String creationDate;
	public String other;
	public String source;
	

	public Favorite(TypeOfResource type, String bookID, String pageLink, String description, int pageNumber, String title,
			String author, String publisher, String creationDate, String other, String source) {
		super();
		this.bookID = bookID;
		this.type = type;
		this.pageLink = pageLink;
		this.description = description;
		this.pageNumber = pageNumber;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.creationDate = creationDate;
		this.other = other;
		this.source = source;
		
	}

	public Favorite(String bookID, String pageLink, String description, int pageNumber,String title,
			String author, String publisher, String creationDate, String other, String source) {
		super();
		this.bookID = bookID;
		this.pageLink = pageLink;
		this.description = description;
		this.pageNumber = pageNumber;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.creationDate = creationDate;
		this.other = other;
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public TypeOfResource getType() {
		return type;
	}

	public void setType(TypeOfResource type) {
		this.type = type;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getPageLink() {
		return this.pageLink;
	}

	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public String toString() {
		return "Favorite [ BookID=" + bookID + ", PageLink=" + pageLink + ", Description=" + description
				+ ", PageNumber=" + pageNumber + "]";
	}

}
